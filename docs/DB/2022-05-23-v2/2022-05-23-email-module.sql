-- ==================== TblEmailTemplate 邮件模板表
DROP TABLE IF EXISTS TblEmailTemplate;

CREATE TABLE TblEmailTemplate
(
    TemplateId          INT NOT NULL IDENTITY ( 1, 1 ), -- 主键自增
    TemplateCode        varchar(255),                   -- 编码
    TemplateName        varchar(255),                   -- 名称
    TemplateDescription varchar(255),                   -- 描述
    TemplateType        varchar(50),                    -- 模板类型：1.ftl，2.html,…
    TemplateStatus      char(1),                        -- 状态：1.启用，2.禁用，3.删除
    CreatedTime         datetime,                       -- 创建时间
    CreatedBy           varchar(255),                   -- 创建人
    UpdatedTime         datetime,                       -- 修改时间
    UpdatedBy           varchar(255),                   -- 修改人
    Remark              varchar(255),                   -- 备注
    PRIMARY KEY (TemplateId)
);

-- ==================== TblEmail 邮件表
DROP TABLE IF EXISTS TblEmail;

CREATE TABLE TblEmail
(
    EmailId             INT NOT NULL IDENTITY ( 1, 1 ), -- 主键自增
    TemplateId          INT,                            -- 邮件模板主键
    EmailPartition      INT,                            -- 邮件分区信息，便于归档和后续大量数据分区，例如：202205
    EmailType           CHAR(1),                        -- 邮件类型：1.即时邮件，2.延时邮件
    EmailFrom           VARCHAR(4000),                  -- 发件人
    EmailTo             VARCHAR(4000),                  -- 收件人
    EmailCc             VARCHAR(4000),                  -- 抄送
    EmailSubject        VARCHAR(4000),                  -- 邮件主题
    EmailBody           NVARCHAR( max),                 -- 正文
    EmailAttachmentName VARCHAR(255),                   -- 邮件附件名称
    EmailAttachmentPath VARCHAR(255),                   -- 邮件附件路径
    EmailAttachmentKey  VARCHAR(255),                   -- 邮件附件秘钥
    CreatedTime         datetime,                       -- 创建时间
    CreatedBy           VARCHAR(255),                   -- 创建人
    UpdatedTime         datetime,                       -- 修改时间
    UpdatedBy           VARCHAR(255),                   -- 修改人
    Remark              VARCHAR(255),                   -- 备注
    PRIMARY KEY (EmailId)
);

-- ==================== TblEmailAttachment 邮件原始附件信息
DROP TABLE IF EXISTS TblEmailAttachment;

CREATE TABLE TblEmailAttachment
(
    AttachmentId   INT NOT NULL IDENTITY ( 1, 1 ), -- 主键自增
    EmailId        INT,                            -- 邮件主键
    AttachmentName VARCHAR(255),                   -- 附件名称
    AttachmentType VARCHAR(255),                   -- 附件类型:1.pdf，2.word，…
    AttachmentPath VARCHAR(255),                   -- 附件存放路径
    CreatedTime    datetime,                       -- 创建时间
    CreatedBy      VARCHAR(255),                   -- 创建人
    UpdatedTime    datetime,                       -- 修改时间
    UpdatedBy      VARCHAR(255),                   -- 修改人
    Remark         VARCHAR(255),                   -- 备注
    PRIMARY KEY (AttachmentId)
);


-- ==================== TblEmailDelayedTask 邮件延时任务表
DROP TABLE IF EXISTS TblEmailDelayedTask;

CREATE TABLE TblEmailDelayedTask
(
    DelayedTaskId            INT NOT NULL IDENTITY ( 1, 1 ), -- 主键自增
    EmailId                  INT,                            -- 邮件主键
    DelayedTaskStatus        CHAR(1),                        -- 任务状态：1.待发送，2.发送中，3.已发送
    DelayedTaskType          CHAR(1),                        -- 发送类型：1.即时发送，2.指定时间发送
    DelayedTaskSpecifiedTime datetime,                       -- 指定发送时间
    RetryTimes               INT,                            -- 重试次数（不包含第一次）
    FailureTimes             INT,                            -- 失败次数
    RecentlySendResult       CHAR(1),                        -- 最近一次发送结果：1.成功，2.失败
    CreatedTime              datetime,                       -- 创建时间
    CreatedBy                VARCHAR(255),                   -- 创建人
    UpdatedTime              datetime,                       -- 修改时间
    UpdatedBy                VARCHAR(255),                   -- 修改人
    Remark                   VARCHAR(255),                   -- 备注
    PRIMARY KEY (DelayedTaskId)
);


-- ==================== TblEmailSendLog 邮件发送日志表
DROP TABLE IF EXISTS TblEmailSendLog;

CREATE TABLE TblEmailSendLog
(
    SendLogId            INT NOT NULL IDENTITY ( 1, 1 ), -- 主键自增
    EmailId              INT,                            -- 邮件主键
    DelayedTaskId        INT,                            -- 延时任务主键
    SendResult           CHAR(1),                        -- 发送结果：1.成功，2.失败
    SendExceptionMessage VARCHAR(255),                   -- 异常信息
    CreatedTime          datetime,                       -- 创建时间
    CreatedBy            varchar(255),                   -- 创建人
    UpdatedTime          datetime,                       -- 修改时间
    UpdatedBy            varchar(255),                   -- 修改人
    Remark               varchar(255),                   -- 备注
    PRIMARY KEY (SendLogId)
);

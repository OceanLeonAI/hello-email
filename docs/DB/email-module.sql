-- ==================== TblEmailTemplate
DROP TABLE IF EXISTS TblEmailTemplate;

CREATE TABLE TblEmailTemplate
(
    EmailTemplateId          INT NOT NULL IDENTITY ( 1, 1 ),
    EmailTemplateName        varchar(255),
    EmailTemplateCode        varchar(255),
    EmailTemplateDescription varchar(255),
    EmailTemplateSuffix      varchar(50),
    EmailTemplateStatus      varchar(1),
    EmailTemplateCreatedTime datetime,
    EmailTemplateCreatedBy   varchar(255),
    EmailTemplateUpdatedTime datetime,
    EmailTemplateUpdatedBy   varchar(255),
    EmailTemplateRemark      varchar(255),
    PRIMARY KEY (EmailTemplateId)
);

-- ==================== TblEmailTask
DROP TABLE IF EXISTS TblEmailTask;

CREATE TABLE TblEmailTask
(
    EmailTaskId          INT NOT NULL IDENTITY ( 1, 1 ),
    EmailTemplateId      INT,
    EmailFrom            VARCHAR(255),
    EmailTo              VARCHAR(255),
    EmailCc              VARCHAR(255),
    EmailSubject         VARCHAR(255),
    EmailText            VARCHAR(50),
    EmailTaskStatus      VARCHAR(1),
    EmailTaskCreatedTime datetime,
    EmailTaskCreatedBy   VARCHAR(255),
    EmailTaskUpdatedTime datetime,
    EmailTaskUpdatedBy   VARCHAR(255),
    EmailTaskRemark      VARCHAR(255),
    PRIMARY KEY (EmailTaskId)
);

-- ==================== TblEmailAttachment
DROP TABLE IF EXISTS TblEmailAttachment;

CREATE TABLE TblEmailAttachment
(
    EmailAttachmentId          INT NOT NULL IDENTITY ( 1, 1 ),
    EmailTaskId                INT,
    EmailAttachmentName        VARCHAR(255),
    EmailAttachmentType        VARCHAR(255),
    EmailAttachmentPath        VARCHAR(255),
    EmailAttachmentCreatedTime datetime,
    EmailAttachmentCreatedBy   VARCHAR(255),
    EmailAttachmentUpdatedTime datetime,
    EmailAttachmentUpdatedBy   VARCHAR(255),
    EmailAttachmentRemark      VARCHAR(255),
    PRIMARY KEY (EmailAttachmentId)
);

-- ==================== TblEmailException
DROP TABLE IF EXISTS TblEmailException;

CREATE TABLE TblEmailException
(
    EmailExceptionId          INT NOT NULL IDENTITY ( 1, 1 ),
    EmailTaskId               INT,
    EmailExceptionName        VARCHAR(255),
    EmailExceptionType        VARCHAR(255),
    EmailExceptionCreatedTime datetime,
    EmailExceptionCreatedBy   VARCHAR(255),
    EmailExceptionUpdatedTime datetime,
    EmailExceptionUpdatedBy   VARCHAR(255),
    EmailExceptionRemark      VARCHAR(255),
    PRIMARY KEY (EmailExceptionId)
);

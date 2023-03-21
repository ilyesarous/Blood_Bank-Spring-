/** table historique **/

CREATE TABLE [Blood_Bank].[donation_history](
	[code] [varchar](255) IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
	[code_patient] [varchar](255) NOT NULL,
	[state] [int] (255) Not Null,
	[observation] [varchar](255) NULL,
	[user_create] [varchar](255) NULL,
	[date_create] [datetime] NULL,
PRIMARY KEY CLUSTERED
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

ALTER TABLE [Blood_Bank].[donation_history]  WITH CHECK ADD  CONSTRAINT [FKgomsu2fgi8uqlhxppkj18tldt] FOREIGN KEY([code_patient])
REFERENCES [Blood_Bank].[Patient] ([code_patient])
GO
ALTER TABLE [Blood_Bank].[donation_history]  WITH CHECK ADD  CONSTRAINT [FKgomsu2fgi8uqlhxppkj18tldt] FOREIGN KEY([state])
REFERENCES [Blood_Bank].[state] ([code])
GO

/** table state **/

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [Blood_Bank].[state](
	[code] [int] IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
	[designation] [nvarchar](20) NULL,
	[designation_sec] [nvarchar](20) NULL,
	[user_create] [nvarchar](50) NULL,
	[create_date] [datetime] NULL,
 CONSTRAINT [PK_risk_state] PRIMARY KEY CLUSTERED
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO




CREATE TABLE [rcm].[revision](
            [id] [int] IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
            [timestamp] [datetime2](7) NULL,
            [user_create] [varchar](255) NOT NULL,
            PRIMARY KEY CLUSTERED
            (
            [id] ASC
            )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
            ) ON [PRIMARY]


CREATE TABLE [rcm].[risk_motive_AUD](
            [code] [int] NOT NULL,
            [REV] [int] NOT NULL,
            [REVTYPE] [smallint] NULL,
            [designation] [nvarchar](200) NOT NULL,
            [designation_sec] [nvarchar](200) NOT NULL,
            [active] [bit] not null,
            [email] [nvarchar](max) NULL,
            [user_create] [nvarchar](50) NULL,
            [create_date] [datetime] NULL,
            CONSTRAINT [PK_risk_motive_AUD] PRIMARY KEY CLUSTERED
            (
            [code] ASC,
            [REV] ASC
            )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
            ) ON [PRIMARY]


            ALTER TABLE [rcm].[risk_motive_AUD]  WITH CHECK ADD  CONSTRAINT [FK_risk_motive_AUD_rev] FOREIGN KEY([REV])
            REFERENCES [rcm].[revision] ([id])

ALTER TABLE [rcm].[risk_motive_AUD] CHECK CONSTRAINT [FK_risk_motive_AUD_rev]

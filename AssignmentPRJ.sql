use assignmentPRJ

--drop table Account
create table Account
(
	IDAcc int not null identity(1,1) primary key (IDAcc),
	Username varchar(50) not null,
	Password varchar(200) not null,
	isAdmin bit
)

SET IDENTITY_INSERT Account OFF

insert into Account (Username, Password, isAdmin)
values ('tuanhd','12345',1)

insert into Account (Username, Password, isAdmin)
values ('huytq','11111',0)

insert into Account (Username, Password, isAdmin)
values ('duclt','222222',0)

insert into Account (Username, Password, isAdmin)
values ('admin321','111201',1)

select * from Account

------------------------------------------------------------
--drop table Template
create table Template
(
	IDTem int not null identity(1,1) primary key (IDTem),
	Name varchar(200) not null
)

insert into Template(Name)  values('Elen')
insert into Template(Name)  values('Patrix')

select * from Template

-------------------------------------------------------------------
--drop table Portfolio
create table Portfolio
(
	IDPortf int not null identity(1,1) primary key (IDPortf),
	NamePortf varchar(200),
	IDAcc int foreign key references Account(IDAcc),
	IDTem int foreign key references Template(IDTem)
)

SET IDENTITY_INSERT Portfolio OFF

insert into Portfolio(NamePortf, IDAcc,IDTem) values('FPT Software',1,2)
insert into Portfolio(NamePortf, IDAcc,IDTem) values('Company A',3,1)
insert into Portfolio(NamePortf, IDAcc,IDTem) values('Company XY',2,2)
insert into Portfolio(NamePortf, IDAcc,IDTem) values('Japanese Company',5,1)
insert into Portfolio(NamePortf, IDAcc,IDTem) values('Little UK',6,2)

insert into Portfolio(NamePortf, IDAcc,IDTem) values('Korean Company',4,1)

select * from Portfolio

insert into PortfolioDetail(IDPortf,NameUser,Gender,Description,Field,Skill,Project,Address,Phone,Email)
values( (select IDPortf from (SELECT TOP 1 * FROM Portfolio ORDER BY IDPortf DESC)as p) ,'Hoang Tuan',1,'I am Tuan','Backend','Youtube-Marketing',
'Kungfutech','Ha noi','384798574','1112t@gmail.com')
-----------------------------------------------------------------
---drop table PortfolioDetail
create table PortfolioDetail
(
	IDDetail int not null identity(1,1),
	IDPortf int unique not null,
	NameUser varchar(200) not null,
	Gender bit not null,
	Description varchar(1000),
	Field varchar(500) not null,
	Skill varchar(500),
	Project varchar(500),
	Address varchar(200),
	Phone bigint not null,
	Email varchar(100) not null
)

ALTER TABLE PortfolioDetail
ADD CONSTRAINT FK_PortfolioDetail_Portfolio FOREIGN KEY(IDPortf) 
    REFERENCES Portfolio(IDPortf);

SET IDENTITY_INSERT PortfolioDetail OFF

insert into PortfolioDetail(IDPortf,NameUser,Gender,Description,Field,Skill,Project,Address,Phone,Email)
values(1,'Hoang Danh Tuan',1,'I am a developer at Java and fullstack','Lap Trinh Web','Spring Boot Framwork-React Js',
'TechMely','Bac Giang','0369591189','tuanhd@gmail.com')

insert into PortfolioDetail(IDPortf,NameUser,Gender,Description,Field,Project,Address,Phone,Email)
values(2,'Le Thien Duc',1,'Just call me by Duc LT','FullStack',
'Smart House','Thanh Hoa','0123456789','duc123@gmail.com')

insert into PortfolioDetail(IDPortf,NameUser,Gender,Field,Skill,Address,Phone,Email)
values(3,'Tran Quang Huy',0,'Thiet Ke Do Hoa','Photoshop-Adope Premier Pro','Ha Noi','0927384382','tranhuy@gmail.com')

insert into PortfolioDetail(IDPortf,NameUser,Gender,Description,Field,Project,Address,Phone,Email)
values(4,'Tony Stark',1,'I am Iron Man in Mavel World','Save The World','Mavel City','US','9374674385','tony.ironman@gmail.com')

insert into PortfolioDetail(IDPortf,NameUser,Gender,Field,Skill,Address,Phone,Email)
values(5,'David Tom',0,'English','IELTS 7.0-Teamwork','Ha Noi','0235486522','david@gmail.com')

select * from PortfolioDetail where Field like '%English%'
-------------------------------------paging--------------------------
  SELECT IDDetail,NamePortf,NameUser,Field,Gender,Address,Phone,Email,Description,Skill,Project,IDTem,Name,IDAcc,Username,Password,isAdmin 
  FROM 
  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,
  a.Description,a.Skill,a.Project,c.IDTem,c.Name,d.IDAcc,d.Username,d.Password,d.isAdmin
  FROM PortfolioDetail a, Portfolio b, Template c, Account d WHERE a.IDPortf = b.IDPortf and b.IDTem = c.IDTem and b.IDAcc = d.IDAcc
  and b.IDAcc = 2 and a.IDDetail = 7) 
  as Paging WHERE rownum >= 1 AND rownum <= 5 
  
  select count(*) from PortfolioDetail where Field like '%ull%' and Gender = 0
  select Field from PortfolioDetail
  
  SELECT COUNT(*) FROM PortfolioDetail WHERE Field LIKE '%f%'
  
  select * from PortfolioDetail
  
  
  SELECT COUNT(*) 
  FROM 
  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,
  a.Description,a.Skill,a.Project,c.IDTem,c.Name,d.IDAcc,d.Username,d.Password,d.isAdmin
  FROM PortfolioDetail a, Portfolio b, Template c, Account d WHERE a.IDPortf = b.IDPortf and b.IDTem = c.IDTem and b.IDAcc = d.IDAcc
  and b.IDAcc = 2 ) 
  as Paging
  
  UPDATE PortfolioDetail SET Address = ?, Description = ?, Email = ?, Field = ?, Gender = ?, NameUser = ?, Phone = ?, Project = ?,
  Skill = ? WHERE IDDetail = ?
  
  UPDATE Portfolio set NamePortf = ? where IDPortf = ?
  
  SELECT IDDetail,IDPortf,NamePortf,NameUser,Field,Gender,Address,Phone,Email,Description,Skill,Project,
		 IDTem,Name,IDAcc,Username,Password,isAdmin 
  FROM 
		 (
		 SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) 
		 as 
		 rownum,a.IDDetail,a.IDPortf,b.NamePortf, a.NameUser,a.Field,a.Gender,
		 a.Address,a.Phone,a.Email,a.Description,a.Skill,a.Project,
		 c.IDTem,c.Name,d.IDAcc,d.Username,d.Password,d.isAdmin
		 
		 FROM 
		 PortfolioDetail a, Portfolio b, Template c, Account d 
		 WHERE a.IDPortf = b.IDPortf and b.IDTem = c.IDTem and b.IDAcc = d.IDAcc AND a.IDDetail = 4
		 
		 ) as Paging 
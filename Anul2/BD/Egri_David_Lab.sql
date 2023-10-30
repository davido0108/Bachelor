/*create database AfacerePirotehnice
go*/
use AfacerePirotehnice
go
/*CREATE TABLE Magazine(
Mid INT PRIMARY KEY ,
Denumire varchar(50),
NrMagazine int,
NrClient int,
Deschis bit
)
Create table Manageri(
Maid INT FOREIGN KEY REFERENCES	Magazine(Mid),
Nume varchar(50) NOT NULL,
Experienta int,
 CONSTRAINT pk_Manageri PRIMARY KEY(Maid)
)
Create table Tip_petarde(
Tpetid INT PRIMARY KEY IDENTITY,
TipPetarde varchar(50) DEFAULT 'Petarde Simple',
NrPetarde int,
PuterePetarde int
)

Create table Petarde(
Pid INT PRIMARY KEY IDENTITY,
Denumire varchar(50),
Cantitate int CHECK(Cantitate>0),
Pret int,
Tpetid int FOREIGN KEY REFERENCES Tip_petarde(Tpetid)
)
Create table Tip_fumigene(
Tfumtid INT PRIMARY KEY IDENTITY,
TipFumigene varchar(50) DEFAULT 'Fumigene Colorate',
NrFumigene int,
Culori varchar(50)
)

Create table Fumigene(
Fid INT PRIMARY KEY IDENTITY,
Denumire varchar(50),
Cantitate int CHECK(Cantitate>0),
Pret int,
Tfumtid int FOREIGN KEY REFERENCES Tip_fumigene(Tfumtid)
)
Create table Tip_Artificii(
Tarid INT PRIMARY KEY IDENTITY,
TipArtificii varchar(50) ,
NrArtificii int,
Culori varchar(50)
)

Create table Artificii(
Aid INT PRIMARY KEY IDENTITY,
Denumire varchar(50),
Cantitate int CHECK(Cantitate>0),
Pret int,
Tarid int FOREIGN KEY REFERENCES Tip_Artificii(Tarid)
)
CREATE TABLE MagazinePetarde(
	Pid INT FOREIGN KEY REFERENCES Petarde(Pid),
	Mid INT FOREIGN KEY REFERENCES Magazine(Mid),
	CONSTRAINT pk_MagazinePetarde PRIMARY KEY(Pid,Mid),
	Capacitate int
)
CREATE TABLE MagazineArtificii(
	Aid INT FOREIGN KEY REFERENCES Artificii(Aid),
	Mid INT FOREIGN KEY REFERENCES Magazine(Mid),
	CONSTRAINT pk_MagazineArtificii PRIMARY KEY(Aid,Mid),
	Capacitate int
)
CREATE TABLE MagazineFumigene(
	Fid INT FOREIGN KEY REFERENCES Fumigene(Fid),
	Mid INT FOREIGN KEY REFERENCES Magazine(Mid),
	CONSTRAINT pk_MagazineFumigene PRIMARY KEY(Fid,Mid),
	Capacitate int
)

Create TABLE AfacerePirotehnice(
Sid INT FOREIGN KEY REFERENCES Sef(Sid),
Maid INT FOREIGN KEY REFERENCES Manageri(Maid),
CONSTRAINT pk_AfacerePirotehnice PRIMARY KEY(Sid,Maid)
)
CREATE TABLE Sef(
Sid INT PRIMARY KEY IDENTITY,
Nume varchar(50),
NrMagazine int,
AniConducereAfacere int
)
ALTER TABLE AfacerePirotehnice
ADD AniInfintareFirma int

ALTER TABLE Manageri 
ADD CONSTRAINT pk_Manageri PRIMARY KEY(Maid)

DROP TABLE Manageri


ALTER TABLE Tip_Artificii
Drop column Culori

ALTER TABLE Magazine
Drop column Deschis
*/

---Tipuri de artificii
INSERT INTO Tip_Artificii(TipArtificii) VALUES('Bombite de artificii'),('Baterii de artificii')

--Artificii

INSERT INTO Artificii(Denumire,Cantitate,Pret,Tarid) 
VALUES ('Dum Bum single shot','4','40',9)

INSERT INTO Artificii(Denumire,Cantitate,Pret,Tarid) 
VALUES ('Thunder flower','6','20',9)

INSERT INTO Artificii(Denumire,Cantitate,Pret,Tarid) 
VALUES ('Dumbum single shot 20mm','10','36',9)

INSERT INTO Artificii(Denumire,Cantitate,Pret,Tarid) 
VALUES ('Show Box 4v1 multicalibru','216','2000',10)

INSERT INTO Artificii(Denumire,Cantitate,Pret,Tarid) 
VALUES ('Aurora','100','435',10)



--Magazine 


INSERT INTO Magazine(Mid,Denumire,NrMagazine)
VALUES ('1','Magazin Petarde','3')
INSERT INTO Magazine(Mid,Denumire,NrMagazine)
VALUES ('2','Fratia Petardelor','3')
INSERT INTO Magazine(Mid,Denumire,NrMagazine)
VALUES ('3','Fratia Petardelor','5')
INSERT INTO Magazine(Mid,Denumire,NrMagazine)
VALUES ('4','Fratia Fumigenelor','1')
INSERT INTO Magazine(Mid,Denumire,NrMagazine)
VALUES ('5','Magazin Fumigene','3')


-- Magazine artificii
INSERT INTO MagazineArtificii(Aid,Mid,Capacitate)
VALUES (3,2,200)
INSERT INTO MagazineArtificii(Aid,Mid,Capacitate)
VALUES (2,3,200)
INSERT INTO MagazineArtificii(Aid,Mid,Capacitate)
VALUES (2,1,200)

-- am incercat sa sterg tarid(tipuri_artificii id)
DELETE FROM Tip_Artificii
WHERE TarId = 8;

SELECT * FROM Tip_Artificii
SELECT * FROM Artificii
SELECT * FROM Magazine
SELECT * FROM MagazineArtificii

Select Distinct Pret 
From Artificii
Select Avg(Pret) as PretMediu
From Artificii

SELECT * FROM Tip_Artificii
SELECT * FROM Artificii

Select c.Tarid , p.Denumire, p.Pret
From Tip_Artificii c, Artificii p
Where c.Tarid = p.Tarid

Select c.Tarid , p.Denumire, p.Pret
From Tip_Artificii c INNER JOIN Artificii p On
c.Tarid = p.Tarid


--Fumigene
ALTER TABLE Tip_fumigene
Drop column NrFumigene
ALTER TABLE Tip_fumigene
Drop column Culori

INSERT INTO Tip_fumigene(TipFumigene)
VALUES ('Fumigene Colorate'),('Fumigene in mana')


---Tip fumigene

INSERT INTO Fumigene(Denumire,Cantitate,Pret,Tfumtid) 
VALUES ('Fumigen PXM40 albastră','4','16',1)

INSERT INTO Fumigene(Denumire,Cantitate,Pret,Tfumtid) 
VALUES ('Magic smoke balls','6','4',1)

INSERT INTO Fumigene(Denumire,Cantitate,Pret,Tfumtid) 
VALUES ('Fumigen PXM40 violet','10','8',1)

INSERT INTO Fumigene(Denumire,Cantitate,Pret,Tfumtid) 
VALUES ('Fumigen PXM40 verde','16','5',1)

INSERT INTO Fumigene(Denumire,Cantitate,Pret,Tfumtid) 
VALUES ('Grenadă de fum manuală portocalie','3','30',2)

INSERT INTO Fumigene(Denumire,Cantitate,Pret,Tfumtid) 
VALUES ('Grenadă de fum manuală albastră','2','30',2)
INSERT INTO Fumigene(Denumire,Cantitate,Pret,Tfumtid) 
VALUES ('Grenadă de fum automata','2','25',2)

SELECT *FROM Tip_fumigene
SELECT * FROM Fumigene

SELECT *FROM Magazine
Select * From MagazineFumigene
-- Magazine fumigene 
INSERT INTO MagazineFumigene(Fid,Mid,Capacitate)
VALUES (1,4,200)
INSERT INTO MagazineFumigene(Fid,Mid,Capacitate)
VALUES (1,5,200)
INSERT INTO MagazineFumigene(Fid,Mid,Capacitate)
VALUES (3,4,200)
INSERT INTO MagazineFumigene(Fid,Mid,Capacitate)
VALUES (5,4,200)
INSERT INTO MagazineFumigene(Fid,Mid,Capacitate)
VALUES (6,5,200)
INSERT INTO MagazineFumigene(Fid,Mid,Capacitate)
VALUES (7,4,200)

Select * FROM Fumigene
Select * FROM MagazineFumigene
Select * FROM Magazine
Select * From Tip_fumigene








--    Pretul fumigenelor tip in ordine descrescatoare,cu magazinele la care se pot gasi 
Select  fum.Denumire,fum.Pret,tfum.TipFumigene,mag.Denumire
From Tip_fumigene tfum INNER JOIN Fumigene fum on tfum.Tfumtid = fum.Tfumtid INNER JOIN MagazineFumigene magfum on fum.Fid= magfum.Fid
INNER JOIN Magazine mag on mag.Mid =magfum.Mid
Group by fum.Denumire,fum.Pret,tfum.TipFumigene,mag.Denumire
Order by Pret DESC



--    Toate grenazile de vanzare ,in ordine crescatoare,la magazinele la care se gasesc
Select fum.Denumire,fum.Pret,tfum.TipFumigene,mag.Denumire
From Tip_fumigene tfum INNER JOIN Fumigene fum on tfum.Tfumtid = fum.Tfumtid INNER JOIN MagazineFumigene magfum on fum.Fid= magfum.Fid
INNER JOIN Magazine mag on mag.Mid =magfum.Mid
Where fum.Denumire Like 'Grenad%'
Order by Pret 

--   Produsele ,la magazinele la care se pot gasi in ordine ,cu pretul > 5 
Select Distinct fum.Fid,fum.Denumire,fum.Pret,mag.Denumire
From Tip_fumigene tfum INNER JOIN Fumigene fum on tfum.Tfumtid = fum.Tfumtid INNER JOIN MagazineFumigene magfum on fum.Fid= magfum.Fid
INNER JOIN Magazine mag on mag.Mid =magfum.Mid
Where Pret>5
Order by mag.Denumire asc

--  Care dintre filiale are peste 4 magazine deschise 
Select  Distinct mag.Denumire,SUM(NrMagazine) as NumarTotalMagazine
From Magazine mag
Group by mag.Denumire,mag.NrMagazine
Having SUM(NrMagazine) >4


--    toate tipurile de produse distincte intre 5-20 de lei
Select Distinct fum.Fid ,fum.Denumire,fum.Pret,tfum.TipFumigene  
From Tip_fumigene tfum INNER JOIN Fumigene fum on tfum.Tfumtid = fum.Tfumtid INNER JOIN MagazineFumigene magfum on fum.Fid= magfum.Fid
INNER JOIN Magazine mag on mag.Mid =magfum.Mid
Where Pret between 5 and 30

Select * From Fumigene


--    medie pret fumigene
Select fum.Fid, fum.Denumire , fum.Pret
From Fumigene fum 
Order by fum.Pret 


-- doar fumigene de vanzare si magazinele in care se gasesc 

Select  fum.Denumire,fum.Pret,tfum.TipFumigene,mag.Denumire
From Tip_fumigene tfum INNER JOIN Fumigene fum on tfum.Tfumtid = fum.Tfumtid INNER JOIN MagazineFumigene magfum on fum.Fid= magfum.Fid
INNER JOIN Magazine mag on mag.Mid =magfum.Mid
where fum.Denumire like 'Fumi%'



--fumigenele care au o cantitate mai mare de 4 si pret mai mare de 4

Select tfum.TipFumigene ,fum.Denumire,fum.Pret,fum.Cantitate
From Tip_fumigene tfum INNER JOIN Fumigene fum on tfum.Tfumtid = fum.Tfumtid INNER JOIN MagazineFumigene magfum on fum.Fid= magfum.Fid
where Cantitate >4 AND Pret>4

--bombite de artificii cu pretul mai mic de 30
Select ta.TipArtificii , ar.Denumire,ar.Cantitate,ar.Pret
From Tip_Artificii ta INNER JOIN Artificii ar on ta.Tarid = ar.Tarid INNER JOIN MagazineArtificii mag on ar.Aid = mag.Aid
where Pret < '45'

--  Pretul mediu al fumigenelor 

Select Sum(Pret)as SumaPret ,Avg(Pret) as PretMediu
From Fumigene 



Create table Versiuni(
Versiune int
)
Select * From Versiuni





CREATE PROCEDURE procedure_1
AS 
BEGIN	
	ALTER TABLE Manageri
	ALTER COLUMN Nume varchar(100)
	update Versiuni set Versiune = 1
	select * from Versiuni
	Print 'Coloana Nume este acum varchar de 100'

END			

EXEC procedure_1


CREATE PROCEDURE reversed_proc_1
AS 
BEGIN
	ALTER TABLE Manageri
	ALTER COLUMN Nume varchar(50)
	update Versiuni set Versiune = 0
	select * from Versiuni
	Print 'Coloana Nume este acum varchar de 50'
END

EXEC reversed_proc_1


CREATE PROCEDURE procedure_2
AS
BEGIN
	ALTER TABLE Manageri
	ADD CONSTRAINT df_2 DEFAULT 2 for Experienta
	update Versiuni set Versiune = 2
	select * from Versiuni
	Print 'Valoarea default pentru Experienta este 2'

END


CREATE PROCEDURE reversed_proc_2
AS 
BEGIN
	ALTER TABLE Manageri
	DROP CONSTRAINT df_2;
	update Versiuni set Versiune = 1
	select * from Versiuni
	Print 'Coloana experienta nu mai are valoarea default = 2'
END


CREATE PROCEDURE procedure_3
AS 
BEGIN
	CREATE TABLE Date_firma(
	Datid int NOT NULL PRIMARY KEY,
	Cifra_Afaceri int,
	An int,
	Nr_angajati int
	);
	update Versiuni set Versiune = 3
	select * from Versiuni
	Print 'Tabelul Date_firma a fost creat'

END

EXEC procedure_3
SELECT * FROM Date_firma



CREATE PROCEDURE reversed_proc_3
AS 
BEGIN
	DROP TABLE Date_firma
	update Versiuni set Versiune = 2
	select * from Versiuni
	Print 'Tabelul Date_firma a fost sters'
END

EXEC reversed_proc_3
SELECT * FROM Date_firma

CREATE PROCEDURE procedure_4
AS 
BEGIN
	ALTER TABLE Date_firma
	ADD Profit int
	update Versiuni set Versiune = 4
	select * from Versiuni
	Print 'Coloana Profit a fost adaugata in tabelul Date_firma'
END

EXEC procedure_4
SELECT * FROM Date_firma

CREATE PROCEDURE reversed_proc_4
AS 
BEGIN
	ALTER TABLE Date_firma
	DROP COLUMN Profit
	update Versiuni set Versiune = 3
	select * from Versiuni
	Print 'Coloana Profit a fost stearsa din tabelul Date_firma'
END


CREATE PROCEDURE procedure_5
AS 
BEGIN
	Create table Firma(
	Firmid INT PRIMARY KEY,
	Datid int CONSTRAINT fk_Firma_DateFirma FOREIGN KEY(Datid) REFERENCES Date_firma(Datid)
	)
	update Versiuni set Versiune = 5
	select * from Versiuni
	Print 'A fost adaugata tabela Firma cu cheia straina din tabela Data_firma'
END


CREATE PROCEDURE reversed_proc_5
AS 
BEGIN
	Drop table Firma
	update Versiuni set Versiune = 4
	select * from Versiuni
	Print 'Tabela Firma a fost stearsa'
End



EXEC procedure_1
EXEC procedure_2
EXEC procedure_3
EXEC procedure_4
EXEC procedure_5

Select * from Date_firma

EXEC reversed_proc_5
EXEC reversed_proc_4
EXEC reversed_proc_3
EXEC reversed_proc_2
EXEC reversed_proc_1

select * from Versiuni

EXEC main 20


EXEC main 5

CREATE OR ALTER PROCEDURE main
@versiune int 
AS 
BEGIN 
	if @versiune < 0 or @versiune >5
		print 'Versiunea introdusa nu este disponibila'
	else
		begin
			declare @text varchar(30)
			if @versiune>(Select Versiune from Versiuni)
			begin
				set @text = 'procedure_'
				declare @vers_curenta int
				set @vers_curenta=(Select Versiune from Versiuni)
				while @vers_curenta<@versiune 
				begin 
					set @text = 'procedure_'
					set @vers_curenta = @vers_curenta +1
					set @text = @text + cast(@vers_curenta as varchar)
					exec @text
				end
			end
			else
			begin
				if @versiune<(Select Versiune from Versiuni)
				begin
					set @text = 'reversed_proc_'
					declare @vers_curenta2 int
					set @vers_curenta2=(Select Versiune from Versiuni)
					while @vers_curenta2>@versiune 
					begin 
						set @text = 'reversed_proc_'
						set @text = @text + cast(@vers_curenta2 as varchar)
						exec @text
						set @vers_curenta2 = @vers_curenta2 - 1
					end
				end
			end
	end
	
end

---Laborator 4

select * from Tables
select * from TestTables
Select * from Tests


INSERT INTO Tables(Name) VALUES ('Tip_Artificii')
INSERT INTO Tables(Name) VALUES ('Artificii')
INSERT INTO Tables(Name) VALUES ('MagazineArtificii')



CREATE View View1 as SELECT * FROM Artificii

Create view View2 as
Select TipArtificii,Denumire,Cantitate from Tip_Artificii Inner join Artificii 
on Tip_Artificii.Tarid  = Artificii.Tarid

Create view View3 as Select Artificii.Aid,Cantitate,Pret from Artificii Inner join MagazineArtificii
on  Artificii.Aid = MagazineArtificii.Aid 
Group by Artificii.Aid,Cantitate,Pret

INSERT INTO Views(Name) VALUES ('View1')
INSERT INTO Views(Name) VALUES ('View2')
INSERT INTO Views(Name) VALUES ('View3')

select * from Tables
Select * from Views

select * from View2
select * from View3


Insert into TestViews Values (3,12),(3,13),(3,14);

create or alter procedure select_view
@nume varchar(30)
AS
BEGIN
	declare @q varchar(100)
	set @q = 'Select * from '+@nume
	exec(@q)
end

create or alter procedure delete_tabel
@nume varchar(30)
as 
begin
	declare @q varchar(100)
	set @q = 'Delete from '+@nume;
	exec(@q)
end

CREATE or ALTER PROCEDURE inserare_Artificii
AS
BEGIN	
	declare @fk int
	Select top 1 @fk = Tarid from Tip_Artificii
	INSERT INTO Artificii VALUES ('Peko Artificii',150,500,@fk)

END

CREATE or ALTER PROCEDURE inserare_MagazineArtificii
AS
BEGIN
	declare @sql varchar(100),@nr int;
	select top 1 @nr = NoOfRows from TestTables where TableID = 6
	set @sql = 'insert into MagazineArtificii select distinct top '+ cast(@nr as varchar(50))+' Aid,Mid from Artificii cross join Magazine'
	exec(@sql)
END

exec inserare_Tip_Artificii
exec inserare_Artificii
exec inserare_MagazineArtificii

select * from MagazineArtificii
select * from Artificii
select * from Tip_Artificii

Insert into MagazineArtificii distinct top 100 Aid,Mid from Artificii cross join Magazine

exec delete_from_ MagazineArtificii
exec delete_from_ Artificii
exec delete_from_ Tip_Artificii

insert into Tests values ('inserare_Tip_Artificii'),('inserare_Artificii'),('inserare_MagazineArtificii')


insert into TestTables Values (2,6,1000,1),(2,5,1000,2),(2,4,1000,3),(4,4,1000,3),(5,5,1000,2),(6,6,1000,1);




create or alter procedure Testare_BD
as 
begin
declare	@table_name nvarchar(50),@pozitie int,@proc nvarchar(50),@vname nvarchar(50),@nr int ,@test int,@id int;
declare @dstart datetime,@dstart_view datetime,@dfinal datetime;	
set @pozitie = 1;
set @dstart = getDate();

--delete
while @pozitie<4
begin 
		select @table_name = Tables.Name
		from Tables inner join TestTables on Tables.TableID = TestTables.TableID inner join Tests on TestTables.TestID = Tests.TestID
		where TestTables.Position = @pozitie and Tests.Name = 'delete_tabel'
		exec delete_tabel @table_name
		set @pozitie = @pozitie + 1
end
--insert
set @pozitie = 3 
while @pozitie>0
begin 
	select @nr = NoOfRows,@proc=Name from TestTables inner join Tests on TestTables.TestID = Tests.TestID where Position=@pozitie and Tests.Name like 'inserare%'
	set @id = 0
	if @pozitie = 1 
		exec @proc;
	else
	begin
		while @id<@nr
		begin
			exec @proc;
			set @id = @id+1;
		end
	end
	set @pozitie = @pozitie-1;

end

-view
set @dstart_view = GETDATE()
select @vname= Name from Views inner join TestViews on Views.ViewID = TestViews.ViewID where TestID = 3 and Views.ViewID = 12;
exec select_view @vname
select @vname= Name from Views inner join TestViews on Views.ViewID = TestViews.ViewID where TestID = 3 and Views.ViewID = 13;
exec select_view @vname
select @vname= Name from Views inner join TestViews on Views.ViewID = TestViews.ViewID where TestID = 3 and Views.ViewID = 14;
exec select_view @vname

set @dfinal = GETDATE()
INSERT INTO TestRuns values ('Teste pt toate tabelele alese',@dstart,@dfinal);
select @test = TestRunID from TestRuns where StartAt=@dstart and EndAt=@dfinal;
insert into TestRunTables values (@test,4,@dstart,@dstart_view),(@test,5,@dstart,@dstart_view),(@test,6,@dstart,@dstart_view);
insert into TestRunViews values (@test,12,@dstart_view,@dfinal),(@test,13,@dstart_view,@dfinal),(@test,14,@dstart_view,@dfinal);

end


select * from MagazineArtificii
select * from Artificii
select * from Tip_Artificii


select * from Tables
select * from TestTables
Select * from Tests

Select * from TestViews
select * from Views

select * from TestRuns
select * from TestRunTables
select * from TestRunViews


exec Testare_BD


select * from AfacerePirotehnice
select * from Manageri
select * from Magazine

---Am creat o tabela cu relatie m-n
create table Distribuitori(
Did INT FOREIGN KEY REFERENCES Artificii(Aid),
Nume varchar(50) not null,
Oras varchar(50) not null,
Cantitate int,
CONSTRAINT pk_Distribuitori PRIMARY KEY(Did)
)


---LAB 5 

---adaug coloane in tabel pentru a avea pe ce sa lucrez
alter table Tip_Artificii
ADD Culoare varchar(50)

alter table Tip_Artificii
ADD Putere int



select dbo.incepe_cu_a('rosu')

CREATE OR ALTER PROCEDURE CRUD_Tip_Aritificii
@tip varchar(50),
@culoare varchar(50),
@putere	 varchar(50),
@noOfRows int

as
begin
	if(dbo.Verifica_putere(@putere)=1 and dbo.incepe_cu_a(@culoare)=1)
	begin
		declare @n int=1
		while @n <= @noOfRows 
		begin
		 insert into Tip_Artificii(TipArtificii,Culoare,Putere) values (@tip,@culoare,@putere)
			set @n = @n+1
		end
	
		select * from Tip_Artificii

		update Tip_Artificii set Culoare = 'rosu' where Putere > 100  
	
		delete from	Tip_Artificii where	Putere like '50'

		print 'CRUD operations for Tip_Artificii executed '
	end
	else if dbo.Verifica_putere(@putere)=1
	begin 
		print 'Culoriile artificiilor trebuie sa fie rosii ,albe,albastre sau galbene.'
		return
	end
	else 
	begin
		print 'Culoriile artificiilor trebuie sa fie rosii ,albe,albastre sau galbene si puterea lor sa fie mai mica decat 1000.'
		return
	end

end


---puterea artificiilor trebuie sa fie mai mica de 1000,iar culoarea lor poate fi doar rosie,galbena sau albastra
exec CRUD_Tip_Aritificii 'Artificii artudo', 'albstru','200',4


select * from Tip_Artificii
select * from Artificii

delete  from Artificii

--resetare identity key
DBCC CHECKIDENT ('dbo.Artificii',RESEED,0);

insert into Artificii(Denumire,Cantitate,Pret,Tarid) Values ('Artific',300,150,1)



create or alter procedure CRUD_Distribuitori
@nume varchar(50),
@oras varchar(50) ,
@cantitate int
as
begin
	if  dbo.test_len(@nume) = 1 and dbo.test_len(@oras) = 1 
	begin
		--insert
		declare @fk 
		select @fk = Max(Aid) 
		from Artificii 

		insert into Distribuitori(Did,Nume,Oras,Cantitate) Values (@fk,@nume,@oras,@cantitate)
		--select 
		select * from Distribuitori
		--update
		update Distribuitori set Cantitate = 400 where Oras like 'Bucuresti'
		--delete
		delete from Distribuitori 
			where Cantitate = 0
			print 'Crud operations for table Distribuitori executed'
	end
	else
		begin
			print 'Denumirea si Numele orasului trebuie sa aiba minim 3 caractere'
		end
end




exec CRUD_Distribuitori 'Arti Fic','Oradea',300
exec CRUD_Distribuitori 'Arti Fic','Bucuresti',300









select * from lab5_view1
select * from lab5_view2

select * from Tip_Artificii
select * from Distribuitori
select * from Artificii

----------------------LAB 5 REFACUT

--creare relatie m-n
CREATE TABLE DepozitArtificii(
	Aid INT FOREIGN KEY REFERENCES Artificii(Aid),
	Deid INT FOREIGN KEY REFERENCES Depozite(Deid),
	CONSTRAINT DepozitArtificii PRIMARY KEY(Aid,Deid),
	Capacitate int
)



CREATE  TABLE Depozite(
Deid INT PRIMARY KEY ,
Nume varchar(50) not null,
Oras varchar(50) not null,
Cantitate int
)

---functii/teste


Create or alter function  dbo.Test_pret5(@p int)
RETURNS INT
AS
 BEGIN
 IF @p >5 SET @p=1
 ELSE SET @p=0
RETURN @p
END

select dbo.Test_pret5(5)
select dbo.Test_pret5(10)


create or alter function test_len(@cuv varchar(50))
returns int
as
	begin
		declare @f int
		declare @s int
		set @s = len(@cuv)
		if @s>3
			set @f = 1
		else 
			set @f = 0
	return @f
end

select dbo.test_len('sd')
select dbo.test_len('sdds')



create function Verifica_putere(@put int)
returns int
as 
	begin
		if @put<1000
			set @put=1
		else set @put=0
			return @put
end

select dbo.Verifica_putere(1000)
select dbo.Verifica_putere(950)

create or alter function verifica_oras(@cuv varchar(50))
returns varchar
as
	begin
	declare @r int

		if @cuv like 'Oradea' or @cuv like 'Bucuresti' or @cuv like 'Cluj-Napoca' or @cuv like 'Constanta'
			set @r=1
		else set @r=0
return @r
end

select dbo.verifica_oras('Oradea')
select dbo.verifica_oras('Bacau')


create or alter procedure CRUD_Artificii
@denumire varchar(50),
@cantitate int ,
@pret int,
@noOfRows int

as
begin
	if dbo.Test_pret5(@pret) = 1 and dbo.test_len(@denumire) = 1 
	begin
		--insert
		declare @fk int
		declare @n int =1

		while @n <= @noOfRows 
		begin
			Select top 1 @fk = Tarid from Tip_Artificii
			INSERT INTO Artificii VALUES (@denumire,@cantitate,@pret,@fk)
				set @n = @n+1
		end

		--select 
		select * from Artificii
		--update
		update Artificii set Pret = 150 where Cantitate>100 
		--delete
		delete from Artificii 
			where Tarid = 0
			print 'Crud operations for table Artificii executed'
		end
	else
		begin
			print 'Pretul trebuie sa fie mai mare de 5 si denumirea produsului sa aibe minim 3 caractere '
		end
end


create or alter procedure CRUD_Depozite
@nume varchar(50),
@oras varchar(50),
@cantitate int
as
begin
	if  dbo.test_len(@nume) = 1 and dbo.test_len(@oras) = 1 and dbo.verifica_oras(@nume)=1
	begin
		--insert
		declare @fk int 
		select @fk = Max(Aid) 
		from Artificii 

		insert into Depozite(Deid,Nume,Oras,Cantitate) Values (@fk,@nume,@oras,@cantitate)
		--select 
		select * from Depozite
		--update
		update Depozite set Cantitate = 400 where Oras like 'Bucuresti'
		--delete
		delete from Depozite 
			where Cantitate = 0
			print 'Crud operations for table Depozite executed'
	end
	else
		begin
			print 'Denumirea si Numele orasului trebuie sa aiba minim 3 caractere,iar orasul selectat trebuie sa fie Bucuresti sau Oradea sau Cluj-Napoca sau Constanta '
		end
end


CREATE or ALTER PROCEDURE inserare_DepoziteArtificii
AS
BEGIN
	declare @sql varchar(100),@nr int;
	select top 1 @nr = NoOfRows from TestTables where TableID = 6
	set @sql = 'insert into DepoziteArtificii select distinct top '+ cast(@nr as varchar(50))+' Aid,Deid from Artificii cross join Depozite'
	exec(@sql)
END

delete from DepoziteArtificii
select * from DepoziteArtificii




create or alter procedure CRUD_DepoziteArtificii
as
begin
	begin
		declare @fk int 
		select @fk = Max(Aid) 
		from Artificii 
		if(dbo.Verifica_limita(@fk)=0)
			begin
				--insert	
				exec inserare_DepoziteArtificii
				--select 
				select * from DepoziteArtificii 
				--update
				update DepoziteArtificii set Aid = 1000 where Aid=500
				--delete
				delete from DepoziteArtificii 
					print 'Crud operations for table DepoziteArtificii executed'
			end
		else 
			begin
				print 'Baza de date este supraincarcata'
			end
	end
end

create or alter function Verifica_limita(@fk int)
returns int
as
	begin	
		if @fk>200 
			set @fk = 1
		else
			set @fk = 0
		return @fk
end




--teste operati CRUD pe tabelul Artificii si  Distribuitori
exec CRUD_Artificii 'RGB',400,200,1
exec CRUD_Artificii 'Artificii sintetice',400,5,1


exec CRUD_Depozite 'Depozit OLX','Oradea',300

select * from Artificii
select * from DepoziteArtificii
select * from Depozite


---executii exemple 
exec CRUD_Artificii 'Artificii pentru ziua de nastere',400,200,1
exec CRUD_Depozite 'Depozit Cargus','Depozit',400
exec CRUD_DepoziteArtificii 




create or alter view lab5_view1 as
select Depozite.Deid,Artificii.Aid,Depozite.Nume,Depozite.Oras,Artificii.Pret
from Artificii,Depozite
Group by Depozite.Deid,Artificii.Aid,Depozite.Nume,Depozite.Oras,Artificii.Pret


create or alter view lab5_view2 as
select Depozite.Deid,Artificii.Denumire,Artificii.Pret
from Depozite
inner join Artificii on Depozite.Deid = Artificii.Aid

select * from Depozite
select * from Artificii

create nonclustered index nindx_Cantitate on Artificii(Cantitate)


create nonclustered index nindx_Nume on Depozite(Nume)


select * from lab5_view1
select * from lab5_view2

use AfacerePirotehnice
go

select * from Artificii
select * from Tip_Artificii


delete from Tip_Artificii
delete from Artificii

INSERT INTO Tip_Artificii (TipArtificii,Culoare,Putere) VALUES ('Artificii zii de nastere','albastru',200)
INSERT INTO Tip_Artificii (TipArtificii,Culoare,Putere) VALUES ('Artificii zii de nastere','rosu',200)

use AfacerePirotehnice
go

Alter table Petarde
Drop column Cantitate

Create table Tip_Rachete(
Trid INT PRIMARY KEY IDENTITY,
Nume varchar(50) 
)

Create table Rachete(
Rid INT PRIMARY KEY IDENTITY,
Bucati int,
Rating int,
Sunet int,
Trid int FOREIGN KEY REFERENCES Tip_Rachete(Trid)
)

INSERT INTO Tip_Rachete (Nume) Values ('Range Rocket'),('Signature Range'),('Fly Hawk'),('Set')

select * from Tip_Rachete
select * from Rachete

Insert into Rachete (Bucati, Rating, Sunet, Trid) Values (20, 9, 200, 1), (35, 7, 200, 2), (5, 8, 150, 1), (17, 7, 75, 3), (15, 4, 25, 4)v
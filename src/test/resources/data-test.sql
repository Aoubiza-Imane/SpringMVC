-- Les données qui seront préchargées dans la base, avant les tests
-- On peut rajouter des données spécifiques pour un test par l'annotation @Sql( ))
-- cf. application-test.properties
INSERT INTO Galerie(id, nom, adresse) VALUES (1, 'Saatchi', 'King\''s Road, Londres');
INSERT INTO Galerie(id, nom, adresse) VALUES (2, 'Christies', 'Saville Road, Londres');
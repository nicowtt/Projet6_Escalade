-- Remplissage base de données--

-- 1/utilisateur (1-2)--

INSERT INTO public.utilisateur
        (raisonsociale, nom, prenom, motdepasse, adresse, codepostal, ville, pays, numerotelephone, email, membreassociation, autre)
VALUES
        ('Mr', 'vega', 'vincent','$2a$10$MzDiElM5YB7rUhzqcM/JNeC7jYEKPBfqxc3hE2COvLjIopUZrHFOK','boulevard tarantino', 31000, 'Toulouse', 'France', '0645123265', 'vincent.vega@gmail.com', true, NULL);

-- 2/utilisateur (2-2)--

INSERT INTO public.utilisateur
        (raisonsociale, nom, prenom, motdepasse, adresse, codepostal, ville, pays, numerotelephone, email, membreassociation, autre)
VALUES
        ('Mme', 'wallace', 'mia','$2a$10$MzDiElM5YB7rUhzqcM/JNeC7jYEKPBfqxc3hE2COvLjIopUZrHFOK','boulevard rodriguez', 31000, 'Toulouse', 'France', '0645877721', 'mia.wallace@gmail.com', false, NULL);
        
        
-- 3/Element 1 pour site (1-1)--

INSERT INTO public.element
        (utilisateur_id)
VALUES
        (1);
        
-- 4/site (1-1)--

INSERT INTO public.site
        (nomsite, descriptionsite, localisationdepartement, localisationpays, urlphotosite, nombredesecteur, officielsite, element_id)
VALUES
        ('En beys', 'Ce site est situé au coeur de la réserve nationale de la faune sauvage d''Orlu. A partir des forges d''Orlu vous devez prendre la route du pont du Bisp et de la réserve d''Orlu. Ensuite dirigez vous vers l''étang d''En Beys, d''abord par une longue piste, 
        puis le sentier GR7. Il y a 2 H de marche en montée jusqu''au refuge au bord du lac.', 'Ariège', 'France', 'https://image.noelshack.com/fichiers/2019/18/2/1556636810-enbays-base.jpg', 2, false, 1);
        
-- 5/Element 2 pour topopapier (1-1)--

INSERT INTO public.element
        (utilisateur_id)
VALUES
        (1);
        
-- 6/topoPapier (1-1)--

INSERT INTO public.topopapier
        (nomtopo, description, nomcreateur, datecreation, datemaj, disponibilite, demandereservation, site_id, element_id)
VALUES
        ('Topo Ariège 1', 'Le topo couvre uniquement le Site En Bays','Alfred jerry', '01-08-2004', null, true, false, 1, 2);

-- 7/Element 3 pour secteur (1-2)--

INSERT INTO public.element
        (utilisateur_id)
VALUES
        (1);


-- 8/secteur (1-2)--

INSERT INTO public.secteur
        (nomsecteur, descriptionsecteur, acces, altitudebase, orientation, typeroche, nombredevoies, cotation, urlphotosecteur, coordonnegps, element_id, site_id)
VALUES
        ('Couillade d''En Beys', '15 mns depuis l''étang d''En Beys. Periode favorable : été', 'Vous devez longer le lac vers la Couillade. 
        Quand le sentier se redresse aller à droite vers la paroi.',null, 'Est', 'Granit', 2, 'de 4b à 6a', 'https://image.noelshack.com/fichiers/2019/18/2/1556636822-enbays-secteur-couillade.jpg', 'Latitude: 42° 37'' 26" N Longitude: 1° 55'' 55" E', 3, 1);
        
-- 9/Element 4 pour secteur (2-2)--

INSERT INTO public.element
        (utilisateur_id)
VALUES
        (1);

-- 10/secteur (2-2)--

INSERT INTO public.secteur
        (nomsecteur, descriptionsecteur, acces, altitudebase, orientation, typeroche, nombredevoies, cotation, urlphotosecteur, coordonnegps, element_id, site_id)
VALUES
        ('Aiguilles d''En Beys', '45mn depuis l''étang d''En  Beys. Periode favorable : été', 'Vous devez passer de l''autre côté du lac jusqu''à la prise d''eau. 
        Suivre  ensuite les trace cairnées dans une pente raide.', 2400, 'Nord Ouest', 'Granit', 5, 'de 2 à 5b', 'https://image.noelshack.com/fichiers/2019/18/2/1556636820-enbays-secteur-aiguille-en-beys.jpg', 'Latitude: 42° 37'' 2" N Longitude: 1° 56'' 50" E', 4, 1);
        
-- 11/Element 5 pour voie (1-2)--

INSERT INTO public.element
        (utilisateur_id)
VALUES
        (1);
        
-- 12/voie secteur couillade (1-2)--

INSERT INTO public.voie
        (numero, nomvoie, tempdescalade, descriptionvoie, longueur, cotation, hauteur, precisionequipement, ouvertureetequipement, dateouverture, statut, element_id, secteur_id)
VALUES
        (1, 'La terrine a juju', 1, 'Belle escalade en excellent rocher. Très jolie 2ème longueur.', '3L', 'TDinf/6a', 100, 'Rappel 90m 8 dégaines + relais', 'Thibaut Lucas et Pierre Périssé', '01-08-2004', 'équipée', 5, 1);

-- 13/Element 6 pour voie (2-2)--

INSERT INTO public.element
        (utilisateur_id)
VALUES
        (1);

-- 14/voie couillade (2-2)--

INSERT INTO public.voie
        (numero, nomvoie, tempdescalade, descriptionvoie, longueur, cotation, hauteur, precisionequipement, ouvertureetequipement, dateouverture, statut, element_id, secteur_id)
VALUES
        (2, 'Le grand nain', 1, 'Escalade en dalle, de quoi apprendre à poser les pieds !', '3L', 'Dinf/4c', 110, 'Rappel 70m 7 dégaines + relais', 'Thibaut Lucas et Pierre Périssé', '01-08-2004', 'équipée', 6, 1);
        
-- 15/Element 7 pour voie (1-5)--

INSERT INTO public.element
        (utilisateur_id)
VALUES
        (1);

-- 16/voie secteur aiguilles (1-5)--

INSERT INTO public.voie
        (numero, nomvoie, tempdescalade, descriptionvoie, longueur, cotation, hauteur, precisionequipement, ouvertureetequipement, dateouverture, statut, element_id, secteur_id)
VALUES
        (1, 'Mal des rimayes', 3, null, '8L', 'T.Dinf/6a 5c oblig', 250, 'Rappel 90m 6 dégaines jeu de friends sangles', 'B. Cathala, J.L. Pomier, C.Pujol', '01-01-1987', 'semi-équipée', 7, 2);

-- 17/Element 8 pour voie (2-5)--

INSERT INTO public.element
        (utilisateur_id)
VALUES
        (1);

-- 18/voie secteur aiguilles (2-5) --

INSERT INTO public.voie
        (numero, nomvoie, tempdescalade, descriptionvoie, longueur, cotation, hauteur, precisionequipement, ouvertureetequipement, dateouverture, statut, element_id, secteur_id)
VALUES
        (2, 'Eperon ouest', 3, null, '10L', 'D.Dinf/5b 4c oblig', 350, 'Rappel 90m 4 dégaines jeu de friends sangles', 'B. Cathala, J.L. Pomier, C.Pujol', '01-01-1987', 'peu équipée', 8, 2);

-- 19/Element 9 pour voie (3-5)--

INSERT INTO public.element
        (utilisateur_id)
VALUES
        (1);

-- 20/voie secteur aiguilles (3-5) --

INSERT INTO public.voie
        (numero, nomvoie, tempdescalade, descriptionvoie, longueur, cotation, hauteur, precisionequipement, ouvertureetequipement, dateouverture, statut, element_id, secteur_id)
VALUES
        (3, 'Aiguille rouge 3', 2, null, '5L', 'D/5c 5b oblig', 200, 'Rappel 90m 6 dégaines jeu de friends sangles', 'B. Cathala, J.L. Pomier, C.Pujol', '01-01-1987', 'semi-équipée', 9, 2);  
        
-- 21/Element 10 pour voie (4-5)--

INSERT INTO public.element
        (utilisateur_id)
VALUES
        (1);

-- 22/voie secteur aiguilles (4-5) --

INSERT INTO public.voie
        (numero, nomvoie, tempdescalade, descriptionvoie, longueur, cotation, hauteur, precisionequipement, ouvertureetequipement, dateouverture, statut, element_id, secteur_id)
VALUES
        (4, 'Aiguille rouge 4', 2, null, '5L', 'D/5b 5b oblig', 200, 'Rappel 90m 6 dégaines jeu de friends pitons,sangles', 'B. Cathala, J.L. Pomier, C.Pujol', '01-01-1987', 'peu équipée', 10, 2);       
   
-- 23/Element 11 pour voie (5-5)--

INSERT INTO public.element
        (utilisateur_id)
VALUES
        (1);

-- 24/voie secteur aiguilles (5-5) --

INSERT INTO public.voie
        (numero, nomvoie, tempdescalade, descriptionvoie, longueur, cotation, hauteur, precisionequipement, ouvertureetequipement, dateouverture, statut, element_id, secteur_id)
VALUES
        (5, 'Aiguille rouge 5', 2, null, '6L', 'D.sup/5c 5b oblig', 200, 'Rappel 90m 6 dégaines jeu de friends sangles', 'B. Cathala, J.L. Pomier, C.Pujol', '01-01-1987', 'semi-équipée', 11, 2);  
        

-- 25/commentaire sur voie (1-4) --

INSERT INTO public.commentaire
        (datecommentaire, element_id, commentaire, utilisateur_id)
VALUES
        ('18-04-2019 10:15:00', 11, 'Pour la descente, depuis le sommet, perdre de l’altitude par des éboulis 
        versant ouest pour retrouver la brèche du canal de Boulaxes que l’on descend pour revenir
        au lac d’en Beys par l’itinéraire de montée.', 1);


-- 26/commentaire sur secteur (2-4) --

INSERT INTO public.commentaire
        (datecommentaire, element_id, commentaire, utilisateur_id)
VALUES
        ('12-04-2019 11:12:00', 4, 'Attention secteur partiellement équipée (pitons, plaquettes) Emporter quelques friends (petites tailles) ou coinceurs, marteau, sangles, casque… ', 1);


-- 27/commentaire sur site (3-4) --

INSERT INTO public.commentaire
        (datecommentaire, element_id, commentaire, utilisateur_id)
VALUES
        ('10-04-2019 12:32:42', 1, 'Possibilité de dormir au refuge (4 dortoirs séparés - 70 personnes possible)', 1);

-- 28/commentaire sur topo papier (4-4) --

INSERT INTO public.commentaire
        (datecommentaire, element_id, commentaire, utilisateur_id)
VALUES
        ('8-04-2019 12:43:32', 2, 'Une nouvelle voie est en cours d''équipement non repertorié dans le topopapier', 1);

-- 31/reservation (1-1) --

--INSERT INTO public.reservation
        --(statusreservation, emailpretok, topopapier_id, utilisateur_id)
--VALUES
        --(true, , null, 1, 1);--
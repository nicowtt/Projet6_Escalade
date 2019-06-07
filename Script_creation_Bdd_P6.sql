
CREATE SEQUENCE public.utilisateur_id_seq;

CREATE TABLE public.Utilisateur (
                id INTEGER NOT NULL DEFAULT nextval('public.utilisateur_id_seq'),
                raisonSociale VARCHAR(50),
                nom VARCHAR(50) NOT NULL,
                prenom VARCHAR(50) NOT NULL,
                motDePasse VARCHAR(100) NOT NULL,
                adresse VARCHAR(100),
                codepostal INTEGER,
                ville VARCHAR(50),
                pays VARCHAR(50),
                numeroTelephone VARCHAR(20),
                email VARCHAR(100) NOT NULL,
                membreAssociation BOOLEAN NOT NULL,
                autre VARCHAR(200),
                CONSTRAINT utilisateur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.utilisateur_id_seq OWNED BY public.Utilisateur.id;

CREATE SEQUENCE public.element_id_seq;

CREATE TABLE public.Element (
                id INTEGER NOT NULL DEFAULT nextval('public.element_id_seq'),
                utilisateur_id INTEGER NOT NULL,
                CONSTRAINT element_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.element_id_seq OWNED BY public.Element.id;

CREATE SEQUENCE public.commentaire_id_seq;

CREATE TABLE public.Commentaire (
                id INTEGER NOT NULL DEFAULT nextval('public.commentaire_id_seq'),
                dateCommentaire TIMESTAMP NOT NULL,
                element_id INTEGER NOT NULL,
                commentaire VARCHAR(500) NOT NULL,
                utilisateur_id INTEGER NOT NULL,
                CONSTRAINT commentaire_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.commentaire_id_seq OWNED BY public.Commentaire.id;

CREATE SEQUENCE public.site_id_seq;

CREATE TABLE public.Site (
                id INTEGER NOT NULL DEFAULT nextval('public.site_id_seq'),
                nomSite VARCHAR(100) NOT NULL,
                descriptionSite VARCHAR(500),
                localisationDepartement VARCHAR(50) NOT NULL,
                localisationPays VARCHAR(50) NOT NULL,
                urlPhotoSite VARCHAR(100),
                nombreDeSecteur INTEGER NOT NULL,
                officielSite BOOLEAN NOT NULL,
                element_id INTEGER NOT NULL,
                CONSTRAINT site_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.site_id_seq OWNED BY public.Site.id;

CREATE SEQUENCE public.secteur_id_seq;

CREATE TABLE public.Secteur (
                id INTEGER NOT NULL DEFAULT nextval('public.secteur_id_seq'),
                nomSecteur VARCHAR(100) NOT NULL,
                descriptionSecteur VARCHAR(500),
                acces VARCHAR(200),
                altitudebase INTEGER,
                orientation VARCHAR(100),
                typeRoche VARCHAR(100),
                nombreDeVoies INTEGER NOT NULL,
                cotation VARCHAR(50) NOT NULL,
                urlPhotoSecteur VARCHAR(100),
                coordonneGps VARCHAR(100),
                element_id INTEGER NOT NULL,
                site_id INTEGER NOT NULL,
                CONSTRAINT secteur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.secteur_id_seq OWNED BY public.Secteur.id;

CREATE SEQUENCE public.voie_id_seq;

CREATE TABLE public.Voie (
                id INTEGER NOT NULL DEFAULT nextval('public.voie_id_seq'),
                numero INTEGER NOT NULL,
                nomVoie VARCHAR(100),
                tempDescalade INTEGER,
                descriptionVoie VARCHAR(500),
                longueur VARCHAR(50),
                cotation VARCHAR(50),
                hauteur INTEGER,
                precisionEquipement VARCHAR(100),
                ouvertureEtEquipement VARCHAR(100),
                dateOuverture VARCHAR,
                statut VARCHAR(50),
                element_id INTEGER NOT NULL,
                secteur_id INTEGER NOT NULL,
                CONSTRAINT voie_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.voie_id_seq OWNED BY public.Voie.id;

CREATE SEQUENCE public.topopapier_id_seq;

CREATE TABLE public.TopoPapier (
                id INTEGER NOT NULL DEFAULT nextval('public.topopapier_id_seq'),
                nomTopo VARCHAR(100) NOT NULL,
                description VARCHAR(200) NOT NULL,
                nomCreateur VARCHAR(50) NOT NULL,
                dateCreation VARCHAR NOT NULL,
                dateMaj VARCHAR,
                disponibilite BOOLEAN NOT NULL,
                demandeReservation BOOLEAN NOT NULL,
                site_id INTEGER NOT NULL,
                element_id INTEGER NOT NULL,
                CONSTRAINT topopapier_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.topopapier_id_seq OWNED BY public.TopoPapier.id;

CREATE SEQUENCE public.reservation_id_seq;

CREATE TABLE public.Reservation (
                id INTEGER NOT NULL DEFAULT nextval('public.reservation_id_seq'),
                statusReservation BOOLEAN NOT NULL,
                emailPretOk VARCHAR,
                topoPapier_id INTEGER NOT NULL,
                utilisateur_id INTEGER NOT NULL,
                CONSTRAINT reservation_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.reservation_id_seq OWNED BY public.Reservation.id;

ALTER TABLE public.Element ADD CONSTRAINT utilisateur_element_fk
FOREIGN KEY (utilisateur_id)
REFERENCES public.Utilisateur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Reservation ADD CONSTRAINT utilisateur_reservation_fk
FOREIGN KEY (utilisateur_id)
REFERENCES public.Utilisateur (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Commentaire ADD CONSTRAINT utilisateur_commentaire_fk
FOREIGN KEY (utilisateur_id)
REFERENCES public.Utilisateur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Site ADD CONSTRAINT element_site_fk
FOREIGN KEY (element_id)
REFERENCES public.Element (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.TopoPapier ADD CONSTRAINT element_topopapier_fk
FOREIGN KEY (element_id)
REFERENCES public.Element (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Secteur ADD CONSTRAINT element_secteur_fk
FOREIGN KEY (element_id)
REFERENCES public.Element (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Voie ADD CONSTRAINT element_voie_fk
FOREIGN KEY (element_id)
REFERENCES public.Element (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Commentaire ADD CONSTRAINT element_commentaire_fk
FOREIGN KEY (element_id)
REFERENCES public.Element (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Secteur ADD CONSTRAINT site_secteur_fk
FOREIGN KEY (site_id)
REFERENCES public.Site (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.TopoPapier ADD CONSTRAINT site_topopapier_fk
FOREIGN KEY (site_id)
REFERENCES public.Site (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Voie ADD CONSTRAINT secteur_voie_fk
FOREIGN KEY (secteur_id)
REFERENCES public.Secteur (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Reservation ADD CONSTRAINT topopapier_reservation_fk
FOREIGN KEY (topoPapier_id)
REFERENCES public.TopoPapier (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;
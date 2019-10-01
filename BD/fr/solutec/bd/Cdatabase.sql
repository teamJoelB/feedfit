-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 01 oct. 2019 à 15:25
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `feedfitbd`
--
CREATE DATABASE IF NOT EXISTS `feedfitbd` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `feedfitbd`;

-- --------------------------------------------------------

--
-- Structure de la table `poids`
--

DROP TABLE IF EXISTS `poids`;
CREATE TABLE IF NOT EXISTS `poids` (
  `idpoids` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) NOT NULL,
  `datepoids` date NOT NULL,
  `valpoids` float NOT NULL,
  PRIMARY KEY (`idpoids`),
  KEY `fk_poids1` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tache`
--

DROP TABLE IF EXISTS `tache`;
CREATE TABLE IF NOT EXISTS `tache` (
  `idtache` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) NOT NULL,
  `idtype` int(11) NOT NULL,
  `datedeb` date NOT NULL,
  `datefin` date NOT NULL,
  `ao` tinyint(1) DEFAULT NULL,
  `valtache` float DEFAULT NULL,
  PRIMARY KEY (`idtache`),
  KEY `fktache1` (`iduser`),
  KEY `fk_tache2` (`idtype`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tache`
--

INSERT INTO `tache` (`idtache`, `iduser`, `idtype`, `datedeb`, `datefin`, `ao`, `valtache`) VALUES
(1, 1, 1, '2019-10-01', '2019-10-01', 1, 10),
(2, 2, 2, '2019-10-02', '2019-10-09', 0, 15);

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

DROP TABLE IF EXISTS `type`;
CREATE TABLE IF NOT EXISTS `type` (
  `idtype` int(11) NOT NULL AUTO_INCREMENT,
  `typetache` varchar(50) NOT NULL,
  PRIMARY KEY (`idtype`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type`
--

INSERT INTO `type` (`idtype`, `typetache`) VALUES
(1, 'Marche'),
(2, 'Course');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `nomuser` varchar(40) NOT NULL,
  `prenomuser` varchar(40) NOT NULL,
  `pseudouser` varchar(40) NOT NULL,
  `mailuser` varchar(100) NOT NULL,
  `mdpuser` varchar(40) NOT NULL,
  `poidsuser` float DEFAULT NULL,
  `tailleuser` float DEFAULT NULL,
  `ageuser` int(11) DEFAULT NULL,
  `sexeuser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`iduser`, `nomuser`, `prenomuser`, `pseudouser`, `mailuser`, `mdpuser`, `poidsuser`, `tailleuser`, `ageuser`, `sexeuser`) VALUES
(1, 'Rocha', 'Claudio', 'crocha', 'crocha@test.com', 'test', 65, 1.78, 24, 'H'),
(2, 'Rocha', 'Claudio', 'crocha', 'crocha@test.com', 'test', 65, 1.78, 24, 'H');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `poids`
--
ALTER TABLE `poids`
  ADD CONSTRAINT `fk_poids1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);

--
-- Contraintes pour la table `tache`
--
ALTER TABLE `tache`
  ADD CONSTRAINT `fk_tache2` FOREIGN KEY (`idtype`) REFERENCES `type` (`idtype`),
  ADD CONSTRAINT `fktache1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

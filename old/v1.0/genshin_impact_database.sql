CREATE DATABASE  IF NOT EXISTS `1773031_genshin_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `1773031_genshin_db`;
-- MariaDB dump 10.17  Distrib 10.4.14-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: 1773031_genshin_db
-- ------------------------------------------------------
-- Server version	10.4.14-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `code`
--

DROP TABLE IF EXISTS `code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(12) DEFAULT NULL,
  `quota` int(11) DEFAULT NULL,
  `expire_date` date DEFAULT NULL,
  `posted_date` date DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code`
--

LOCK TABLES `code` WRITE;
/*!40000 ALTER TABLE `code` DISABLE KEYS */;
INSERT INTO `code` VALUES (9,'GENSHINGIFT',124,'2021-04-10','2021-04-08',2),(10,'TT7BVJNPL249',24,'2021-04-14','2021-04-08',2),(11,'SBNBUK67M37',252,'2021-04-21','2021-04-08',1),(12,'PSNTC8FEQK4',0,'2021-04-28','2021-04-08',3);
/*!40000 ALTER TABLE `code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbuser`
--

DROP TABLE IF EXISTS `dbuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `isadmin` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbuser`
--

LOCK TABLES `dbuser` WRITE;
/*!40000 ALTER TABLE `dbuser` DISABLE KEYS */;
INSERT INTO `dbuser` VALUES (1,'qweqwe','qweqwe',0),(2,'qweqweq','qweqwe',0),(3,'asdasd','asdasd',0),(4,'haryoharyo','haryoharyo',0),(5,'admin','qweqwe',1),(6,'timok','timoknakal',0),(7,'zxczxc','zxczxc',0),(8,'fghfghfgh','fghfghfgh',0),(9,'test5','qweqwe',0),(10,'test6','qweqwe',0);
/*!40000 ALTER TABLE `dbuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `element`
--

DROP TABLE IF EXISTS `element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `picture` varchar(500) DEFAULT NULL,
  `description` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `element`
--

LOCK TABLES `element` WRITE;
/*!40000 ALTER TABLE `element` DISABLE KEYS */;
INSERT INTO `element` VALUES (1,'Electro','https://rerollcdn.com/GENSHIN/Elements/Element_Electro.png','Continuously drains Energy Recharge.'),(2,'Hydro','https://rerollcdn.com/GENSHIN/Elements/Element_Hydro.png','Increases skill CD durations.'),(3,'Pyro','https://rerollcdn.com/GENSHIN/Elements/Element_Pyro.png','Continuously inflicts damage over time.'),(4,'Cyro','https://rerollcdn.com/GENSHIN/Elements/Element_Cryo.png','Increases stamina consumption.'),(5,'Dendro','https://rerollcdn.com/GENSHIN/Elements/Element_Dendro.png','Ambush.'),(6,'Geo','https://rerollcdn.com/GENSHIN/Elements/Element_Geo.png','Increases damage taken by 35%.'),(11,'Anemo','https://rerollcdn.com/GENSHIN/Elements/Element_Anemo.png','Increase movement speed.');
/*!40000 ALTER TABLE `element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genshin_character`
--

DROP TABLE IF EXISTS `genshin_character`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genshin_character` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_element` int(11) DEFAULT NULL,
  `id_weapon_type` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `picture` varchar(500) DEFAULT NULL,
  `picture_hd` varchar(500) DEFAULT NULL,
  `description` varchar(2500) DEFAULT NULL,
  `normal_attack_name` varchar(150) DEFAULT NULL,
  `description_normal_attack` varchar(2500) DEFAULT NULL,
  `elemental_skill_name` varchar(150) DEFAULT NULL,
  `description_elemental_skill` varchar(2500) DEFAULT NULL,
  `elemental_burst_name` varchar(150) DEFAULT NULL,
  `description_elemental_burst` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `character_to_element_idx` (`id_element`),
  KEY `character_to_weapon_type_idx` (`id_weapon_type`),
  CONSTRAINT `character_to_element` FOREIGN KEY (`id_element`) REFERENCES `element` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `character_to_weapon_type` FOREIGN KEY (`id_weapon_type`) REFERENCES `weapon_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genshin_character`
--

LOCK TABLES `genshin_character` WRITE;
/*!40000 ALTER TABLE `genshin_character` DISABLE KEYS */;
INSERT INTO `genshin_character` VALUES (1,3,4,'Hu Tao','https://rerollcdn.com/GENSHIN/Characters/Hu%20Tao.png','https://uploadstatic-sea.mihoyo.com/contentweb/20210224/2021022415504933030.png','Hu Tao is the 77th Director of the Wangsheng Funeral Parlor, a person vital to managing Liyue\'s funerary affairs.','Secret Spear of Wangsheng','Performs up to 6 consecutive spear strikes.','Guide to Afterlife','Hu Tao consumes a portion of her HP to knock the surrounding enemies back and enter the Paramita Papilio state.  Paramita Papilio Increases Hu Tao\'s ATK based on her Max HP at the time of entering this state. ATK Bonus gained this way cannot exceed 400% of Hu Tao\'s Base ATK. Converts attack DMG to Pyro DMG, which cannot be overridden by any other elemental infusion. Charged attacks apply the Blood Blossom effect to the enemies hit. Increases Hu Tao\'s resistance to interruption. Blood Blossom Enemies affected by Blood Blossom will take Pyro DMG every 4s. This DMG is considered Elemental Skill DMG. Each enemy can be affected by only one Blood Blossom effect at a time, and its duration may only be refreshed by Hu Tao herself.  Paramita Papilio ends when its duration is over, or Hu Tao has left the battlefield or fallen.','Spirit Soother','Commands a blazing spirit to attack, dealing Pyro DMG in a large AoE. Upon striking the enemy, regenerates a percentage of Hu Tao\'s Max HP. This effect can be triggered up to 5 times, based on the number of enemies hit. If Hu Tao\'s HP is below or equal to 50% when the enemy is hit, both the DMG and HP Regeneration are increased.'),(2,6,5,'Albedo','https://rerollcdn.com/GENSHIN/Characters/Albedo.png','https://uploadstatic-sea.mihoyo.com/contentweb/20201216/2020121612011980560.png','An alchemist based in Mondstadt, in the service of the Knights of Favonius.','Favonius Bladework - Weiss','Perform up to 5 rapid strikes.','Abiogenesis: Solar Isotoma','Albedo creates a Solar Isotoma using alchemy, which deals AoE Geo DMG on appearance.  Solar Isotoma When enemies within the Solar Isotoma zone are hit, the Solar Isotoma will generate Transient Blossoms which deal AoE Geo DMG. DMG dealt scales off Albedo\'s DEF. Transient Blossoms can only be generated once every 2s. When a character is located at the locus of the Solar Isotoma, the Solar Isotoma will accumulate Geo power to form a crystallized platform that lifts the character up to a certain height. Only one crystallized platform can exist at a time. Solar Isotoma is considered a Geo construct. Hold to designate the location of the skill.','Rite of Progeniture: Tectonic Tide','Under Albedo\'s command, Geo crystals surge and burst forth, dealing AoE Geo DMG in front of him. If a Solar Isotoma created by Albedo himself is on the field, 7 Fatal Blossoms will be generated in the Solar Isotoma field, bursting violently into bloom and dealing AoE Geo DMG  Tecotonic Tide DMG and Fatal Blossom DMG will not generate Transient Blossoms.'),(3,3,1,'Amber','https://rerollcdn.com/GENSHIN/Characters/Amber.png','https://uploadstatic-sea.mihoyo.com/contentweb/20191009/2019100915010998860.png','Always energetic and full of life, Amber\'s the best - albeit only - Outrider of the Knights of Favonius.','Sharpshooter','Performs up to 5 consecutive shots with a bow.','Explosive Puppet','Baron Bunny Continuously taunts the enemy, drawing their fire. Baron Bunny\'s HP scales with Amber\'s Max HP. When destroyed or when its timer expires, Baron Bunny explodes, dealing AoE Pyro DMG. Hold Adjusts the throwing direction of Baron Bunny. The longer the button is held, the further the throw.','Fiery Rain','Fires of a shower of arrows, dealing continuous AoE Pyro DMG.'),(4,2,2,'Barbara','https://rerollcdn.com/GENSHIN/Characters/Barbara.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200609/2020060916001098585.png','Every denizen of Mondstadt adores Barbara. However, she learned the word \"idol\" from a magazine.','Whisper of Water','Perform up to 4 water splashes attacks that deal Hydro DMG.','Let the Show Begin','Summons water droplets resembling musical notes that form a Melody Loop, dealing Hydro DMG to surrounding enemies and afflicting them with the Wet status.  Melody Loop Barbara\'s Normal Attacks heal all party members and nearby allied characters for a certain amount of HP, which scales with Barbara\'s Max HP. Her Charged Attack generates 4 times the amount of healing. Regenerates a certain amount of the current character\'s HP at regular intervals. Applies the Wet status to the character and enemies who come in contact with them.','Shining Miracle','Heals friendly forces and all parties for a large amount of HP that scales with Barbara\'s Max HP.'),(5,1,3,'Beidou','https://rerollcdn.com/GENSHIN/Characters/Beidou.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200227/2020022720570237519.png','Beidou is the leader of the Crux — an armed fleet based in Liyue Harbor. An armed fleet means exactly what it sounds like: a fleet of ships armed to the teeth.','Oceanborne','Performs up to 5 successive strikes.','Tidecaller','Nothing to worry about. Should anyone raise a hand against her or her men, she will avenge it ten-fold with sword and thunder.  Press Accumulating the power of lightning, Beidou swings her blade forward fiercely, dealing Electro DMG.  Hold Lifts her weapon up as a shield. Max DMG absorbed scales off Beidou\'s Max HP. Attacks using the energy stored within the greatsword upon release or once this ability\'s duration expires, dealing Electro DMG. DMG dealt scales with the number of times Beidou is attacked in the skill\'s duration. The greatest DMG Bonus will be attained once this effect is triggered twice. The shield possesses the following properties: Has 250% Electro DMG Absorption Efficiency. Applies the Electro element to Beidou upon activation.','Stormbreaker','Recalling her slaying of the great beast Haishan. Beidou calls upon that monstrous strength and the lightning to create a Thunderbeast\'s Targe around herself.  Thunderbeast\'s Target When Normal and Charged Attacks hit, they create a lightning discharge that can jump between enemies, dealing Electro DMG. Increases the character\'s resistance to interruption, and decreases DMG taken.'),(6,3,5,'Bennett','https://rerollcdn.com/GENSHIN/Characters/Bennett.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200312/2020031220284864612.png','A righteous and good-natured adventurer from Mondstadt who\'s unfortunately extremely unlucky.','Strike of Fortune','Perform up to 5 rapid strikes.','Passion Overload','Bennett puts all his fire and passion for adventuring into his sword. Results may very based on how fired up he is.  Press A single, swift flame strike that deals Pyro DMG.  Hold (Short) Charges up, resulting in different effects when unleashed based on the Charge Level.  Level 1: Strikes twice, dealing Pyro DMG and launching enemies. Level 2: Unleashes 3 consecutive attacks that deal impressive Pyro DMG, but the last attack triggers an explosion that launches both Bennet and the enemy. Bennett takes no damage from being launched.','Fantastic Voyage','Bennett leaps towards the enemy and performs a plunging attack, dealing Pyro DMG, creating an Inspiration Field.  Inspiration Field If the health of a character in the circle is equal to or falls below 70%, their health will continuously regenerate. Regeneration scales based on Bennett\'s Max HP. If the health of a character in the circle is higher than 70%, they gain an ATK Bonus that is based on Bennett\'s Base ATK. Applies the Pyro element to characters within the Field.'),(7,4,3,'Chongyun','https://rerollcdn.com/GENSHIN/Characters/Chongyun.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200602/2020060221230216012.png','A young exortcist from a family of exorcists. He does everything he can to suppress his pure positive energy.','Demonbane','Performs up to 4 consecutive strikes.','Spirit Blade - Chonghua\'s Layered Frost','Chongyun strikes the ground with his greatsword, causing a Cryo explosion in a circular AoE in front of him that deals Cryo DMG. After a short delay, the cold air created by the Cryo explosion will coalesce into a Chonghua Frost Field, within which all DMG done through Normal and Charged Attacks by Sword, Greatsword and Polearm-wielding characters will be converted to Cryo DMG.','Spirit Blade - Cloud-parting Star','Performing the secret hand seals, Chongyun summons 3 giant spirit blades in mid-air that fall to the earth one by one after a short delay, exploding as they hit the ground. When the spirit blades explode, they will deal AoE Cryo DMG and launch enemies.'),(8,3,3,'Diluc','https://rerollcdn.com/GENSHIN/Characters/Diluc.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200609/2020060916003518753.png','The tycoon of a winery empire in Mondstadt, unmatched in every possible way.','Tempered Sword','Performs up to 4 consecutive strikes.','Searing Onslaught','Performs a forward slash that deals Pyro DMG.  This skill can be used 3 times consecutively. Enters CD if not cast again within a short period.','Dawn','Releases intense flames to knock nearby enemies back, dealing Pyro DMG. The flames then converge into the weapon, summoning a Phoenix that flies forward and deals massive Pyro DMG to all enemies in its path. The Phoenix explodes upon reaching its destination, causing a large amount of AoE Pyro DMG. The searing flames that run down his blade cause Diluc\'s Normal and Charged Attacks to deal Pyro DMG for a time.'),(9,4,1,'Diona','https://rerollcdn.com/GENSHIN/Characters/Diona.png','https://uploadstatic-sea.mihoyo.com/contentweb/20201106/2020110619442567135.png','A young lady who has inherited trace amounts of non-human blood. She is the incredible popular bartender of the Cat\'s Tail tavern.','Katzlein Style','Performs up to 5 consecutive shots with a bow.','Icy Paws','Fires an Icy Paw that deals Cryo DMG to opponents and forms a shield on hit. The shield\'s DMG Absorption scales based on Diona\'s Max HP, and its duration scales off the number of Icy Paws that hit their target.','Signature Mix','Tosses out a special cold brew that deals AoE Cryo DMG and creates a Drunken Mist in an AoE.  Drunken Mist Deals continuous Cryo DMG to opponents within the AoE. Continuously regenerates the HP of characters within the AoE.'),(10,1,1,'Fischl','https://rerollcdn.com/GENSHIN/Characters/Fischl.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200402/2020040211352384080.png','A mysterious girl who calls herself \"Prinzessia der Verurteilung\" and travels with a night raven named Oz.','Bolts of Downfall','Performs up to 5 consecutive shots with a bow.','Nightrider','Summons Oz, the night raven forged of darkness and lightning descends upon the land, dealing Electro DMG in a small AoE. For the ability\'s duration, Oz will continuously attack nearby enemies with Freikugel.  Hold to adjust the location Oz will be summoned to. Press again any time during the ability\'s duration to once again summon him to Fischl\'s side.','Midnight Phantasmagoria','Summons Oz to spread his twin swings of twilight and defend Fischl. Has the following properties during the ability\'s duration: Fischl takes on Oz\'s form, greatly increasing her Movement Speed. Strikes nearby enemies with lightning, dealing Electro DMG to enemies she comes into contact with. Each enemy can only be struck once. Once this ability\'s effects end, Oz will remain on the battlefield and attack his Prinzessin\'s foes. If Oz is already on the field, then this will reset the duration of his presence.'),(11,4,1,'Ganyu','https://rerollcdn.com/GENSHIN/Characters/Ganyu.png','https://uploadstatic-sea.mihoyo.com/contentweb/20210105/2021010519312258008.png','The secretary at Yuehai Pavilion. The blood of the qilin, an illuminated beast, flows within her veins.','Liutian Archery','Performs up to 6 consecutive shots with a bow.','Trail of the Qilin','Leaving a single Ice Lotus behind, Ganyu dashes backward, shunning all impurity and dealing AoE Cryo DMG.','Celestial Shower','Coalesces atmospheric frost and snow to summon a Sacred Cryo Pearl that exorcises evil. During its ability duration, the Sacred Cryo Pearl will continuously rain down shards of ice, striking opponents within an AoE and dealing Cryo DMG.'),(12,11,5,'Jean','https://rerollcdn.com/GENSHIN/Characters/Jean.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200616/2020061611250789508.png','The righteous and rigorous Dandelion Knight, and Acting Grand Master of the Knights of Favonius in Mondstadt.','Favonius Bladework','Performs up to 5 consecutive strikes.','Gale Blade','Focusing the might of the formless wind around her blade, Jean releases a miniature storm, launching enemies in the direction she aims at, dealing massive Anemo DMG.  Hold At the cost of continued Stamina consumption, Jean can command the whirlwind to pull surrounding enemies towards her front. Direction can be adjusted. Character is immobile during skill duration.','Dandelion Breeze','Calling upon the wind\'s protection, Jean creates a swirling Dandelion Field, launching surrounding enemies and causing Anemo DMG. At the same time, she instantly regenerates a large amount of HP for nearby allied units and all party members. HP restored scale off Jean\'s ATK.  Dandelion Field Continuously regenerates HP for one ally and imbues them with the Anemo attribute. Deals Anemo DMG to enemies entering or exiting the field.'),(13,4,5,'Kaeya','https://rerollcdn.com/GENSHIN/Characters/Kaeya.png','https://uploadstatic-sea.mihoyo.com/contentweb/20191009/2019100912053991447.png','A thinker in the Knights of Favonius with a somewhat exotic appearance.','Ceremonial Bladework','Performs up to 5 consecutive strikes.','Frostgnaw','Unleashes a frigit blast, dealing Cryo DMG to enemies in front of Kaeya.','Glacial Waltz','Coalescing the frost in the air, Kaeya summons 3 icicles that revolve around him. These icicles will follow the character around and deal Cryo DMG to enemies in their path for so long as they persist.'),(14,1,5,'Keqing','https://rerollcdn.com/GENSHIN/Characters/Keqing.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200903/2020090320312094830.png','The Yuheng of the Liyue Qixing. Has much to say about Rex Lapis\' unilateral approach to policymaking in Liyue - but in truth, gods admire skeptics such as her quite a lot.','Yunlai Swordsmanship','Performs up to 5 rapid strikes.','Stellar Restoration','Hurls a Lightning Stiletto that annihilates her enemies like the swift thunder. When the Stiletto hits its target, it deals Electro DMG to enemies in a small AoE, and places a Stiletto Mark on the spot hit.  Hold Hold to adjust the direction in which the Stiletto shall be thrown. Stilettos thrown by the Hold attack mode can be suspended in mid-air, allowing Keqing to jump to them when using Stellar Restoration a second time.  Lightning Stiletto If Keqing uses Stellar Restoration again or uses a Charged Attack while its duration lasts, it will clear the Stiletto Mark and produce different effects: If she uses Stellar Restoration again, she will blink to the location of the Mark and unleashed one slashing attack that deals AoE Electro DMG. When blinking to a Stiletto that was thrown from a Holding attack, Keqing can leap across obstructing terrain. If Keqing uses a Charged Attack, she will ignite a series of thundering cuts at the Mark\'s location, dealing AoE Electro DMG.','Starward Sword','Keqing unleashes the power of lightning, dealing Electro DMG in an AoE.  She then blends into the shadow of her blade, striking a series of thunderclap-blows to nearby enemies simultaneous that deal multiple instance of Electro DMG. The final attack deals massive AoE Electro DMG.'),(15,3,2,'Klee','https://rerollcdn.com/GENSHIN/Characters/Klee.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200103/2020010313535536575.png','An explosives expert and a regular at the Knights of Favonius\' confinement room. Also known as Fleeing Sunlight.','Kaboom!','Throws things that go boom when they hit things! Perform up to 3 explosive attacks, dealing AoE Pyro DMG.','Jumpy Dumpty','Jumpy Dumpty is tons of boom-bang-fun! When thrown, Jumpy Dumpty bounces thrice, igniting and dealing AoE Pyro DMG with every bounce.  On the third bounce, the bomb splits into many mines. The mines will explode upon contact with enemies, or after a short period of time, dealing AoE Pyro DMG.  Starts with 2 charges.','Sparks \'n\' Splash','Klee\'s Blazing Delight! For the duration of this ability, continuously summons Sparks \'n\' Splash to attack nearby enemies, dealing AoE Pyro DMG.'),(16,1,2,'Lisa','https://rerollcdn.com/GENSHIN/Characters/Lisa.png','https://uploadstatic-sea.mihoyo.com/contentweb/20191009/2019100915381741181.png','The languid but knowledgeable Librarian of the Knights of Favonius who was deemed by Sumeru Academia to be their most distinguised graduate in the past two centuries.','Lightning Touch','Perform up to 4 lightning attacks that deal Electro DMG.','Violet Arc','Channels the power of lightning to sweep bothersome matters away.','Lightning Rose','Summons a Lightning Rose that unleashes powerful lightning bolts, launching surrounding enemies and dealing Electro DMG. The Lightning Rose will continuously emit lightning to knock back enemies and deal Electro DMG for so long as it persists.'),(17,2,2,'Mona','https://rerollcdn.com/GENSHIN/Characters/Mona.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200903/2020090319255490505.png','A mysterious young astrologer who proclaims herself to be \"Astrologist Mona Megistus,\" and who possesses abilities to match the title.','Ripple of Fate','Performs up to 4 water splash attacks that deal Hydro DMG.','Reflection of Doom','Creates an illusory Phantom of fate from coalesced waterspouts. The Phantom has the following special properties:  Continuously taunts nearby enemies, attracting their fire. Continuously deals Hydro DMG to nearby enemies. When its duration expires, the Phantom explodes, dealing AoE Hydro DMG. Hold Utilizes water currents to move backwards swiftly before conjuring a Phantom. Only one Phantom created by Mirror Reflection of Doom can exist at any time.','Stellaris Phantasm','Mona summons the sparkling waves and creates a reflection of the starry sky, applying the Illusory Bubble status to opponents in a large AoE.  Illusory Bubble Traps opponents inside a pocket of destiny and also makes them Wet. Renders weaker opponents immobile. When an opponent affected by Illusory Bubble sustains DMG, the following effects are produced:  Applies an Omen to the opponent, which gives a DMG Bonus, also increasing the DMG of the attack that causes it. Removes the Illusory Bubble, dealing Hydro Elemental DMG in the process. The DMG Bonus does not apply to the Hydro Elemental DMG dealt in this instance. Omen During its duration, increases DMG taken by enemies.'),(18,6,2,'Ningguang','https://rerollcdn.com/GENSHIN/Characters/Ningguang.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200305/2020030520210453836.png','The Tianquan of Liyue Qixing. Her wealth is unsurpassed in all of Teyvat.','Sparkling Scatter','Shoots gems that deal Geo DMG. Upon hit, this grants Ningguang 1 Star Jade.','Jade Screen','Ningguang creates a Jade Screen out of gold, obsidian and her great opulence, dealing AoE Geo DMG.  Jade Screen Blocks enemy projectiles. Endurance scales based on Ningguang\'s Max HP. Jade Screen is considered a Geo Construct and can be used to block certain attacks, but cannot be climbed. Only one Jade Screen may exist at any one time.','Starshatter','Gathering a great number of gems, Ningguang scatters them all at once, sending homing projectiles at her enemies that deal massive Geo DMG. If Starshatter is cast when a Jade Screen is nearby, the Jade Screen will fire additional gem projectiles at the same time.'),(19,6,3,'Noelle','https://rerollcdn.com/GENSHIN/Characters/Noelle.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200315/2020031518042552922.png','A maid in the service of the Knights of Favonius that dreams of joining their ranks someday.','Favonius Bladework - Maid','Performs up to 4 consecutive strikes.','Breastplate','Summons a protective stone armor, dealing Geo DMG to surrounding enemies and creating a shield. The shield\'s DMG Absorption scaled based on Noelle\'s DEF. The shield has the following properties: When Noelle\'s Normal and Charged Attacks hit a target, they have a certain chance to regenerate HP for all characters, both on and off the field. Applies the Geo element to the character. Possesses 250% Absorption Efficiency against Geo DMG. The amount of HP healed when regeneration is triggered scales based on Noelle\'s DEF.','Sweeping Time','Gathering the strength of stone around her weapon, Noelle strikes the enemies surrounding her within a large AoE, dealing Geo DMG. Afterwards, Noelle gains the following effects: Larger attack AoE. Converts attack DMG to Geo DMG. Increased ATK that scales based on her DEF.'),(20,4,5,'Qiqi','https://rerollcdn.com/GENSHIN/Characters/Qiqi.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200903/2020090318240891919.png','An apprentice and herb-picker Bubu Pharmacy. An undead with a bone-white complexion, she seldom has much in the way of words or emotion.','Ancient Sword Art','Performs up to 5 rapid strikes.','Adeptus Art: Herald of Frost','Using the Icevein Talisman, Qiqi brings forth the Herald of Frost, dealing Cryo DMG to nearby enemies.  Herald of Frost When Qiqi hits a target with her Normal or Charged Attacks, she regenerates HP for all party members and all nearby allied characters. Healing scales based on Qiqi\'s ATK. Regenerates HP for current character at regular intervals. Follows the character around, dealing Cryo DMG to enemies in its path.','Adeptus Art: Preserver of Fortune','Qiqi releases the adeptus power sealed within her body, marking nearby enemies with a Fortune-Preserving Talisman that deals Cryo DMG.  Fortune-Preserving Talisman When enemies affected by this Talisman take DMG, the character that dealt this DMG regenerates HP.'),(21,1,3,'Razor','https://rerollcdn.com/GENSHIN/Characters/Razor.png','https://uploadstatic-sea.mihoyo.com/contentweb/20191028/2019102814534358619.png','A boy who lives among the wolves in Wolvendom of Mondstadt, away from human civilization. As agile as lightning.','Steel Fang','Performs up to 4 consecutive strikes.','Claw and Thunder','Hunts his prey using the techniques taught to him by his master and his Lupical.  Press Swings the Thunder Wolf Claw, dealing Electro DMG to enemies in front of Razor. Upon striking an enemy, Razor will gain an Electro Sigil, which increases his Energy Recharge rate. Razor can have up to 3 Electro Sigils simultaneously, and gaining a new Electro Sigil refreshes their duration.  Hold Gathers Electro energy to unleash a lightning storm over a small AoE, causing massive Electro DMG, and clears all of Razor\'s Electro Sigils. Each Electro Sigil cleared in this manner will be converted into Energy for Razor.','Lightning Fang','Summons the Wolf Within which deals Electro DMG to all nearby opponents. This clears all of Razor\'s Electro Sigils, which will be converted into elemental energy for him. The Wolf Within will fight alongside Razor for the skill\'s duration.  The Wolf Within Strikes alongside Razor\'s normal attacks, dealing Electro DMG. Raises Razor\'s ATK SPD and Electro RES. Causes Razor to be immune to DMG inflicted by the Electro-Charged status. Disables Razor\'s Charged Attacks. Increases Razor\'s resistance to interruption'),(22,11,2,'Sucrose','https://rerollcdn.com/GENSHIN/Characters/Sucrose.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200723/2020072319404862499.png','An alchemist filled with curiosity about all things. She researches bio-alchemy.','Wind Spirit Creation','Performs up to 4 attacks using Wind Spirits, dealing Anemo DMG.','Astable Anemohypostasis Creation - 6308','Creates a small Wind Spirit that deals Anemo DMG to enemies in an AoE, pulling them towards the location of the Wind Spirit before launching them.','Forbidden Creation - Isomer 75 / Type II','Sucrose hurls an unstable concoction that creates a Large Wind Spirit. While it persists, the Large Wind Spirit will continuously pull and launch nearby enemies, dealing AoE Anemo DMG.  Elemental Absorption If the Wind Spirit comes into contact with Hydro / Pyro / Cryo / Electro elements, it will deal additional DMG of that type. Elemental Absorption may only occur once per use.'),(23,2,1,'Tartaglia','https://rerollcdn.com/GENSHIN/Characters/Tartaglia.png','https://uploadstatic-sea.mihoyo.com/contentweb/20201103/2020110321594513532.png','No. 11 of The Harbingers, also known as \"Childe\". His name is highly feared on the battlefield.','Cutting Torrent','Performs up to 6 consecutive shots with a bow.','Foul Legacy: Raging Tide','Unleashes a set of weaponry made of pure water, dealing Hydro DMG to surrounding opponents and entering a Melee Stance. In this Stance, Tartaglia\'s Normal and Charged Attacks change as follows:  Normal Attack Performs up to 6 consecutive Hydro strikes.  Charged Attack Consumes a certain amount of Stamina to unleash a cross slash, dealing Hydro DMG.  Riptide Slash Hitting an opponent affected by Riptide with a melee attack unleashes a Riptide Slash that deals AoE Hydro DMG. DMG dealt in this way is considered Elemental Skill DMG, and can only occur once every 1.5s.  After 30s, or when the ability is unleashed again, this skill will end. Tartaglia will return to his Ranged Stance and this ability will enter CD. The longer Tartaglia stays in his Melee Stance, the longer the CD. If the return to a ranged stance occurs automatically after 30s, the CD is even longer.','Havor: Obliteration','Performs different attacks based on what stance Tartaglia is in when casting.  Ranged Stance: Flash of Havoc Swiftly fires a Hydro-imbued magic arrow, dealing AoE Hydro DMG and applying the Riptide status. Returns a portion of its Energy Cost after use.  Melee Stance: Light Obliteration Performs a slash with a large AoE, dealing massive Hydro DMG to all surrounding opponents, which triggers Riptide Blast.  Riptide Blast When the obliterating waters hit an opponent affected by Riptide, it clears their Riptide status and triggers a Hydro Explosion that deals AoE Hydro DMG. DMG dealt in this way is considered Elemental Burst DMG.'),(24,11,1,'Venti','https://rerollcdn.com/GENSHIN/Characters/Venti.png','https://uploadstatic-sea.mihoyo.com/contentweb/20191122/2019112211503453956.png','One of the many bards of Mondstadt, who freely wanders the city\'s streets and alleys.','Divine Marksmanship','Performs up to 6 consecutive shots with a bow.','Skyward Sonnet','O wind upon which all hymns and songs fly, bear these earth-walkers up into the sky!  Press Summons a Wind Domain at the enemy\'s location, dealing AoE Anemo DMG and launching enemies into the air.  Hold Summons an even larger Wind Domain with Venti as the epicenter, dealing AoE Anemo DMG and launching affected enemies into the air. After unleashing the Hold version of this ability, Venti rides the wind into the air.  Enemies hit by Skyward Sonnet will fall to the ground slowly.','Wind\'s Grand Ode','Fires off an arrow made of countless coalesced winds, creating a huge Stormeye that sucks in objects and enemies along its path, dealing continuous Anemo DMG.  Elemental Absorption If the Stormeye comes into contact with Hydro/Pyro/Cryo/Electro elements, it will deal additional elemental DMG of that type. Elemental Absorption may only occur once per use.'),(25,3,4,'Xiangling','https://rerollcdn.com/GENSHIN/Characters/Xiangling.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200316/2020031619220471258.png','A renowned chef from Liyue. She\'s extremely passionate about cooking and excels at making her signature hot and spicy dishes.','Dough-Fu','Performs up to 5 consecutive spear strikes.','Guoba Attack','Summons Guoba the Panda. Guoba continuously breathes fire, dealing AoE Pyro DMG.','Pyronado','Displaying her mastery over both fire and polearms, Xiangling sends a Pyronado whirling around her. The Pyronado will move with your character for so long as the ability persists, dealing Pyro DMG to all enemies in its path.'),(26,11,4,'Xiao','https://rerollcdn.com/GENSHIN/Characters/Xiao.png','https://uploadstatic-sea.mihoyo.com/contentweb/20210122/2021012210221445691.png','A yaksha adeptus that defends Liyue. Also heralded as the \"Conqueror of Demons\" or \"Vigilant Yaksha.\"','Whirlwind Thrust','Performs up to 6 consecutive spear strikes.','Lemniscatic Wind Cycling','Xiao lunges forward, dealing Anemo DMG to opponents in his path.  Can be used in mid-air. Starts with 2 charges. Generates 3 elemental particles when hits at least 1 target.','Bane of All Evil','Xiao dons the Yaksha Mask that set gods and demons trembling millennia ago.  Yaksha\'s Mask: Greatly increases Xiao\'s jumping ability. Increases his attack AoE and attack DMG. Converts attack DMG into Anemo DMG, which cannot be overriden by any other elemental infusion. In this state, Xiao will continuously lose HP. The effects of this skill end when Xiao leaves the field.'),(27,2,5,'Xingqiu','https://rerollcdn.com/GENSHIN/Characters/Xingqiu.png','https://uploadstatic-sea.mihoyo.com/contentweb/20200324/2020032418395960509.png','A young man carrying a longsword who is frequently seen at book booths. He has a chivalrous heart and yearns for justice and fairness for all.','Guhua Style','Performs up to 5 rapid strikes.','Guhua Sword - Fatal Rainscreen','Xingqiu performs twin strikes with his sword, dealing Hydro DMG. At the same time, this ability creates the maximum number of Rain Swords, which will orbit the character. The Rain Swords have the following properties: When a character takes DMG, the Rain Sword will shatter, reducing the amount of DMG taken. Increases the character\'s resistance to interruption. 20% of Xingqiu\'s Hydro DMG Bonus will be converted to additional DMG Reduction for the Rain Swords.  The maximum amount of additional DMG Reduction that can be gained this way is 24%. The initial maximum number of Rain Swords is 3. Using this ability applies the Wet status onto the character.','Guhua Sword - Raincutter','Initiate Rainbow Bladework and fight using an illusory sword rain, while creating the maximum number of Rain Swords.  Rainbow Bladework Normal Attacks will trigger consecutive sword rain attacks, dealing Hydro DMG. Rain Swords will remain at the maximum number throughout the ability\'s duration. These effects carry over to other characters.'),(28,3,3,'Xinyan','https://rerollcdn.com/GENSHIN/Characters/Xinyan.png','https://uploadstatic-sea.mihoyo.com/contentweb/20201125/2020112517003018442.png','Liyue\'s sole rock \'n\' roll musician. She rebels against ossified prejudices using her music and passionate singing.','Dance on Fire','Performs up to 4 consecutive strikes.','Sweeping Fervor','Xinyan brandishes her instrument, dealing Pyro DMG on nearby enemies, forming a shield made out of her audience\'s passion. The shield\'s DMG Absorption scales based on Xinyan\'s DEF and on the number of enemies hit.  Hitting 0-1 enemies grants Shield Level 1: Ad Lib. Hitting 2 enemies grants Shield Level 2: Lead-In. Hitting 3 or more enemies grants Shield Level 3: Rave, which will also deal intermittent Pyro DMG to nearby enemies. The shield has the following special properties:  When unleashed, it infuses Xinyan with Pyro. It has 250% DMG Absorption effectiveness against Pyro DMG.','Riff Revolution','Strumming rapidly, Xinyan launches nearby enemies and deals Physical DMG to them, hyping up the crowd. The sheer intensity of the atmosphere will cause explosions that deal Pyro DMG to nearby enemies.'),(29,6,4,'Zhongli','https://rerollcdn.com/GENSHIN/Characters/Zhongli.png','https://uploadstatic-sea.mihoyo.com/contentweb/20201123/2020112310562899194.png','A mysterious guest invited by the Wangsheng Funeral Parlor. Extremely knowledgeable in all things.','Rain of Stone','Performs up to 6 consecutive spear strikes.','Dominus Lapidis','Press Commands the omnipresent power of the earth to solidify into a Stone Stele, standing 30 seconds, dealing AoE Geo DMG at the creation.  Hold Causes nearby Geo energy to explode, causing the following effects:  If their maximum number hasn\'t been reached, creates a Stone Stele. Creates a shield of jade. The shield\'s DMG Absorption scales based on Zhongli\'s Max HP. Possesses 150% DMG Absorption against all Elemental and Physical DMG. Deals AoE Geo DMG. If there are nearby targets with the Geo element, it will drain a large amount of Geo element from a maximum of 2 such targets. This effect does not cause DMG. Stone Stele: When created, deals AoE Geo DMG. Additionally, every 2 seconds, it will resonate with other nearby Geo Constructs, dealing Geo DMG to surrounding opponents. Stele creation and Resonance generate 0.5 elemental particles. The Stone Stele is considered a Geo Construct that can both be climbed and used to block attacks. Only one Stele created by Zhongli himself may initially exist at any one time.  Jade Shield Possesses 150% DMG Absorption against all Elemental and Physical DMG. Characters protected by the Jade Shield will decrease the Elemental RES and Physical RES of opponents in a small AoE by 20%. This effect cannot be stacked.','Planet Befall','Brings a falling meteor down to earth, dealing massive Geo DMG to opponents caught in its AoE and applying the Petrification status to them.  Petrification Opponents affected by the Petrification status cannot move.');
/*!40000 ALTER TABLE `genshin_character` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reedem_code_history`
--

DROP TABLE IF EXISTS `reedem_code_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reedem_code_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `id_code` int(11) DEFAULT NULL,
  `reedem_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `reedem_code_history_to_user_idx` (`id_user`),
  KEY `reedem_code_history_to_code_idx` (`id_code`),
  CONSTRAINT `reedem_code_history_to_code` FOREIGN KEY (`id_code`) REFERENCES `code` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reedem_code_history_to_user` FOREIGN KEY (`id_user`) REFERENCES `dbuser` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reedem_code_history`
--

LOCK TABLES `reedem_code_history` WRITE;
/*!40000 ALTER TABLE `reedem_code_history` DISABLE KEYS */;
INSERT INTO `reedem_code_history` VALUES (5,1,10,'2021-04-08 14:14:33'),(6,1,12,'2021-04-08 14:17:34'),(7,1,9,'2021-04-08 22:00:06');
/*!40000 ALTER TABLE `reedem_code_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_character_mark`
--

DROP TABLE IF EXISTS `user_character_mark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_character_mark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `id_character` int(11) DEFAULT NULL,
  `isown` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_character_mark_to_character_idx` (`id_character`),
  KEY `user_character_mark_to_user_idx` (`id_user`),
  CONSTRAINT `user_character_mark_to_character` FOREIGN KEY (`id_character`) REFERENCES `genshin_character` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_character_mark_to_user` FOREIGN KEY (`id_user`) REFERENCES `dbuser` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_character_mark`
--

LOCK TABLES `user_character_mark` WRITE;
/*!40000 ALTER TABLE `user_character_mark` DISABLE KEYS */;
INSERT INTO `user_character_mark` VALUES (1,1,4,0),(2,1,13,0),(3,1,26,0),(4,1,25,0),(5,1,15,0),(6,1,27,0),(7,1,28,0),(8,1,29,0),(9,1,19,0),(10,1,7,0),(11,1,5,0),(12,1,3,0),(13,1,20,0),(14,1,17,0),(15,6,14,0),(16,6,12,0),(17,6,11,0),(18,6,29,0),(19,6,17,0),(20,6,23,0),(21,6,21,0),(22,6,22,0),(23,6,3,0),(24,6,18,0),(25,1,2,0),(26,1,16,0),(27,1,14,0),(28,1,18,0),(29,1,12,0),(30,1,11,0),(31,1,1,0),(32,1,23,0),(33,1,10,0),(34,1,6,0),(35,1,24,0),(36,1,22,0),(37,1,21,0),(38,1,9,0),(39,1,8,0),(40,10,5,0),(41,10,26,1),(42,10,29,1),(43,10,1,1),(44,10,14,0),(45,10,13,0),(46,10,11,0),(47,10,8,0),(48,10,21,1),(49,10,28,1);
/*!40000 ALTER TABLE `user_character_mark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weapon_type`
--

DROP TABLE IF EXISTS `weapon_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weapon_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `picture` varchar(500) DEFAULT NULL,
  `description` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weapon_type`
--

LOCK TABLES `weapon_type` WRITE;
/*!40000 ALTER TABLE `weapon_type` DISABLE KEYS */;
INSERT INTO `weapon_type` VALUES (1,'Bow','https://rerollcdn.com/GENSHIN/Weapon/NEW/Bow.png','bows boast long-ranged attacks in the game, with charged attacks that are capable of hitting monsters as far as the eye can see with the proper aim.'),(2,'Catalyst','https://rerollcdn.com/GENSHIN/Weapon/NEW/Catalyst.png','Due to their elemental nature, all Normal and Charged Attacks performed with a Catalyst are considered elemental damage.'),(3,'Claymore','https://rerollcdn.com/GENSHIN/Weapon/NEW/Claymore.png','Claymores restrict the character wielding them to slower hits than other melee weapons like Swords and Polearms, but those hits deal much more damage per swing.'),(4,'Polearm','https://rerollcdn.com/GENSHIN/Weapon/NEW/Polearm.png','They have the fastest attacks of all the weapons and good forward reach.'),(5,'Sword','https://rerollcdn.com/GENSHIN/Weapon/NEW/Sword.png','Sword attacks are the middle ground of melee weapons, the damage and speed of their attacks are somewhere in between the slow attacks of Claymores and the rapid attacks of Polearms.');
/*!40000 ALTER TABLE `weapon_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-09  8:32:11

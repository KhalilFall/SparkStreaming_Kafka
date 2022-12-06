## Intégration Spark Streaming + Kafka

#### Installation de Kafka dans une machine windows

- Téléchargez et Installez Java 8 ( https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html#license-lightbox )
- Téléchargez kafka ( https://dlcdn.apache.org/kafka/3.1.2/kafka_2.12-3.1.2.tgz )
- Créez un dossier "Kafka" dans C:\ puis y extraire le fichier télécharger précédemment.
- Créez un nouveau dossier "logs" dans le dossier "Kafka"
- Puis dans le dossier "logs", créez y 2 autres dossiers, "zookeeper_log" et "kafka_log"
- Dans le fichier "C:\Kafka\config\zookeeper.Properties", modifiez la valeur de **dataDir** avec le chemin "C:\Kafka\logs\zookeeper_log
- Dans le fichier "C:\Kafka\config\server.Properties", modifiez la valeur de **log.dir** avec le chemin "C:\Kafka\logs\kafka_log

#### Démarrage du server Kafka

- Posiionnez vous dans le répertoire "C:\Kafka\bin\windows" puis lancez les 2 commandes suivantes dans l'ordre
  1. ./zookeeper-server-start.bat ../../config/zookeeper.properties
  2. ./kafka-server-start.bat ../../config/server.properties
  
 #### Commandes Kafka utiles
 
 - Lister les tous topics : ./kafka-topics.bat --list --bootstrap-server localhost:9092
 - Créer un topic : ./kafka-topics.bat --create --bootstrap-server localhost:9092 --topic votreTopic --partitions 1 --replication-factor 1
 - Décrire un topic : ./kafka-topics.bat --describe --bootstrap-server localhost:9092 --topic votreTopic
 - Lancer un producer à partir du console : ./kafka-console-producer.bat  --broker-list localhost:9092 --topic votreTopic
 - Lancer un consumer à partir du console : ./kafka-console-consumer.bat  --bootstrap-server localhost:9092 --topic votreTopic --from-beginning

#### Démo
1) Assurez vous que la partie **Démarrage du server Kafka** est effective
2) Créez un topic kafka
3) Lancer un producer à partir du console pour allimenter le topic créé précédemment
4) Ouvrez le projet Spark dans IntelliJ puis modifiez la ligne 30 pour renseigner le nom du topic créer dans l'étape 2 puis exécutez le programme.
5) Copiez quelques lignes à partir des fichiers json contenus dans le dossier jsons_covid puis collez les dans le producer de l'étape 3.
6) Si tout se passe bien, à chaque fois que vous mettez des informations dans le producer, les données seront visible dans le programme Spark.

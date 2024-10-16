# pocKafkaEsKibana
POC kafka elasticsearch kibana

## Kafka

### Version kafka_2.12-3.8.0

    - cd kafka_2.12-3.8.0
    -- INICIAR KAFKA
    bin\windows\zookeeper-server-start.bat config\zookeeper.properties
    bin\windows\kafka-server-start.bat config\server.properties
    
    -- CREAR TOPIC
    bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --create --topic finytec-topic --partitions 5 --replication-factor 1
    
    bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --create --topic finytec-transactions --partitions 5 --replication-factor 4
    
    
    -- LISTAR TOPIC
    bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
    
    -- VER DEFINICION DE UN TOPIC
    bin\windows\kafka-topics.bat --describe --topic finytec-topic --bootstrap-server localhost:9092
    bin\windows\kafka-topics.bat --describe --topic finytec-transactions --bootstrap-server localhost:9092
    
    -- MODIFICANDO UN TOPIC
    bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --alter --topic finytec-topic --partitions 10
    
    
    -- VERIFICANDO CONFIGURACION VALIDAS PARA EL TOPIC
    bin\windows\kafka-configs.bat --bootstrap-server localhost:9092 --entity-type topics --entity-name finytec-topic --describe
    
    bin\windows\kafka-configs.bat --bootstrap-server localhost:9092 --entity-type topics --entity-name finytec-transactions --describe
    
    -- AGREGANDO CONFIGURACIONES A UN TOPIC
    bin\windows\kafka-configs.bat --bootstrap-server localhost:9092 --entity-type topics --entity-name finytec-topic --alter --add-config x=y (ERROR x no es un elemento de configs del topico)
    
    bin\windows\kafka-configs.bat --bootstrap-server localhost:9092 --entity-type topics --entity-name finytec-topic --alter --add-config retention.ms=604800000
    
    
    --BORRANDO UN TOPIC
    bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --delete --topic finytec-topic
    
    -- CREAR PRODUCER
    bin\windows\kafka-console-producer.bat  --topic finytec-topic  --bootstrap-server localhost:9092
    
    bin\windows\kafka-console-producer.bat  --topic finytec-transactions  --bootstrap-server localhost:9092
    
    -- CREAR CONSUMER
    bin\windows\kafka-console-consumer.bat  --topic finytec-topic --from-beginning  --bootstrap-server localhost:9092 --property print.key=true --property key.separator="-"
    
    bin\windows\kafka-console-consumer.bat  --topic finytec-transactions --from-beginning  --bootstrap-server localhost:9092 --property print.key=true --property key.separator="-"
    
    
    -- BORRAR INFORMACION
    rm -rf \tmp\kafka-logs \tmp\zookeeper

## ElasticSearch

### Version elasticsearch-7.9.0

    - cd  elasticsearch-7.9.0/bin
    - elasticsearch

#### YML
    
    # ======================== Elasticsearch Configuration =========================
    #
    # NOTE: Elasticsearch comes with reasonable defaults for most settings.
    #       Before you set out to tweak and tune the configuration, make sure you
    #       understand what are you trying to accomplish and the consequences.
    #
    # The primary way of configuring a node is via this file. This template lists
    # the most important settings you may want to configure for a production cluster.
    #
    # Please consult the documentation for further information on configuration options:
    # https://www.elastic.co/guide/en/elasticsearch/reference/index.html
    #
    # ---------------------------------- Cluster -----------------------------------
    #
    # Use a descriptive name for your cluster:
    #
    #cluster.name: my-application
    #
    # ------------------------------------ Node ------------------------------------
    #
    # Use a descriptive name for the node:
    #
    #node.name: node-1
    #
    # Add custom attributes to the node:
    #
    #node.attr.rack: r1
    #
    # ----------------------------------- Paths ------------------------------------
    #
    # Path to directory where to store the data (separate multiple locations by comma):
    #
    #path.data: /path/to/data
    #
    # Path to log files:
    #
    #path.logs: /path/to/logs
    #
    # ----------------------------------- Memory -----------------------------------
    #
    # Lock the memory on startup:
    #
    #bootstrap.memory_lock: true
    #
    # Make sure that the heap size is set to about half the memory available
    # on the system and that the owner of the process is allowed to use this
    # limit.
    #
    # Elasticsearch performs poorly when the system is swapping the memory.
    #
    # ---------------------------------- Network -----------------------------------
    #
    # Set the bind address to a specific IP (IPv4 or IPv6):
    #
    #network.host: 192.168.0.1
    #
    # Set a custom port for HTTP:
    #
    #http.port: 9200
    #
    # For more information, consult the network module documentation.
    #
    # --------------------------------- Discovery ----------------------------------
    #
    # Pass an initial list of hosts to perform discovery when this node is started:
    # The default list of hosts is ["127.0.0.1", "[::1]"]
    #
    #discovery.seed_hosts: ["host1", "host2"]
    #
    # Bootstrap the cluster using an initial set of master-eligible nodes:
    #
    #cluster.initial_master_nodes: ["node-1", "node-2"]
    #
    # For more information, consult the discovery and cluster formation module documentation.
    #
    # ---------------------------------- Gateway -----------------------------------
    #
    # Block initial recovery after a full cluster restart until N nodes are started:
    #
    #gateway.recover_after_nodes: 3
    #
    # For more information, consult the gateway module documentation.
    #
    # ---------------------------------- Various -----------------------------------
    #
    # Require explicit names when deleting indices:
    #
    #action.destructive_requires_name: true

## Kibana

    - Version kibana-7.9.0
    - cd kibana-7.9.0/bin
    - kibana.bat

### YML

    # Kibana is served by a back end server. This setting specifies the port to use.
    #server.port: 5601
    
    # Specifies the address to which the Kibana server will bind. IP addresses and host names are both valid values.
    # The default is 'localhost', which usually means remote machines will not be able to connect.
    # To allow connections from remote users, set this parameter to a non-loopback address.
    #server.host: "localhost"
    
    # Enables you to specify a path to mount Kibana at if you are running behind a proxy.
    # Use the `server.rewriteBasePath` setting to tell Kibana if it should remove the basePath
    # from requests it receives, and to prevent a deprecation warning at startup.
    # This setting cannot end in a slash.
    #server.basePath: ""
    
    # Specifies whether Kibana should rewrite requests that are prefixed with
    # `server.basePath` or require that they are rewritten by your reverse proxy.
    # This setting was effectively always `false` before Kibana 6.3 and will
    # default to `true` starting in Kibana 7.0.
    #server.rewriteBasePath: false
    
    # The maximum payload size in bytes for incoming server requests.
    #server.maxPayloadBytes: 1048576
    
    # The Kibana server's name.  This is used for display purposes.
    #server.name: "your-hostname"
    
    # The URLs of the Elasticsearch instances to use for all your queries.
    #elasticsearch.hosts: ["http://localhost:9200"]
    
    # When this setting's value is true Kibana uses the hostname specified in the server.host
    # setting. When the value of this setting is false, Kibana uses the hostname of the host
    # that connects to this Kibana instance.
    #elasticsearch.preserveHost: true
    
    # Kibana uses an index in Elasticsearch to store saved searches, visualizations and
    # dashboards. Kibana creates a new index if the index doesn't already exist.
    #kibana.index: ".kibana"
    
    # The default application to load.
    #kibana.defaultAppId: "home"
    
    # If your Elasticsearch is protected with basic authentication, these settings provide
    # the username and password that the Kibana server uses to perform maintenance on the Kibana
    # index at startup. Your Kibana users still need to authenticate with Elasticsearch, which
    # is proxied through the Kibana server.
    #elasticsearch.username: "kibana_system"
    #elasticsearch.password: "pass"
    
    # Enables SSL and paths to the PEM-format SSL certificate and SSL key files, respectively.
    # These settings enable SSL for outgoing requests from the Kibana server to the browser.
    #server.ssl.enabled: false
    #server.ssl.certificate: /path/to/your/server.crt
    #server.ssl.key: /path/to/your/server.key
    
    # Optional settings that provide the paths to the PEM-format SSL certificate and key files.
    # These files are used to verify the identity of Kibana to Elasticsearch and are required when
    # xpack.security.http.ssl.client_authentication in Elasticsearch is set to required.
    #elasticsearch.ssl.certificate: /path/to/your/client.crt
    #elasticsearch.ssl.key: /path/to/your/client.key
    
    # Optional setting that enables you to specify a path to the PEM file for the certificate
    # authority for your Elasticsearch instance.
    #elasticsearch.ssl.certificateAuthorities: [ "/path/to/your/CA.pem" ]
    
    # To disregard the validity of SSL certificates, change this setting's value to 'none'.
    #elasticsearch.ssl.verificationMode: full
    
    # Time in milliseconds to wait for Elasticsearch to respond to pings. Defaults to the value of
    # the elasticsearch.requestTimeout setting.
    #elasticsearch.pingTimeout: 1500
    
    # Time in milliseconds to wait for responses from the back end or Elasticsearch. This value
    # must be a positive integer.
    #elasticsearch.requestTimeout: 30000
    
    # List of Kibana client-side headers to send to Elasticsearch. To send *no* client-side
    # headers, set this value to [] (an empty list).
    #elasticsearch.requestHeadersWhitelist: [ authorization ]
    
    # Header names and values that are sent to Elasticsearch. Any custom headers cannot be overwritten
    # by client-side headers, regardless of the elasticsearch.requestHeadersWhitelist configuration.
    #elasticsearch.customHeaders: {}
    
    # Time in milliseconds for Elasticsearch to wait for responses from shards. Set to 0 to disable.
    #elasticsearch.shardTimeout: 30000
    
    # Time in milliseconds to wait for Elasticsearch at Kibana startup before retrying.
    #elasticsearch.startupTimeout: 5000
    
    # Logs queries sent to Elasticsearch. Requires logging.verbose set to true.
    #elasticsearch.logQueries: false
    
    # Specifies the path where Kibana creates the process ID file.
    #pid.file: /var/run/kibana.pid
    
    # Enables you to specify a file where Kibana stores log output.
    #logging.dest: stdout
    
    # Set the value of this setting to true to suppress all logging output.
    #logging.silent: false
    
    # Set the value of this setting to true to suppress all logging output other than error messages.
    #logging.quiet: false
    
    # Set the value of this setting to true to log all events, including system usage information
    # and all requests.
    #logging.verbose: false
    
    # Set the interval in milliseconds to sample system and process performance
    # metrics. Minimum is 100ms. Defaults to 5000.
    #ops.interval: 5000
    
    # Specifies locale to be used for all localizable strings, dates and number formats.
    # Supported languages are the following: English - en , by default , Chinese - zh-CN .
    #i18n.locale: "en"
    
    elasticsearch.username: "kibana_system"
    elasticsearch.password: "gab97dan99"

## Logstash

### Version logstash-8.15.2

#### inicio de servicio:  .\logstash.bat -f config\logstash.conf

#### logstash.conf

    input {
    # Entrada para user-service
    tcp {
    port => 5044  # Puerto para user-service
    codec => json_lines
    type => "user-service"
    }
    
        # Entrada para user-event-consumer
        tcp {
            port => 5045  # Puerto para user-event-consumer
            codec => json_lines
            type => "user-event-consumer"
        }
        # Entrada para search-service
        tcp {
            port => 5046  # Puerto para user-event-consumer
            codec => json_lines
            type => "search-service"
        }
        # Entrada para transaction-producer
        tcp {
            port => 5047  # Puerto para transaction-producer
            codec => json_lines
            type => "transaction-producer"
        }
        # Entrada para transaction-consumer
        tcp {
            port => 5048  # Puerto para transaction-consumer
            codec => json_lines
            type => "transaction-consumer"
        }
    }
    
    filter {
    # Filtro de timestamp para ambos servicios
    date {
    match => ["timestamp", "ISO8601"]  # Asegúrate de que tu campo de timestamp esté en formato ISO
    target => "@timestamp"
    }
    }
    
    output {
    # Salida a Elasticsearch para user-service
    if [type] == "user-service" {
    elasticsearch {
    hosts => ["http://localhost:9200"]
    index => "user-service-logs-%{+YYYY.MM.dd}"
    manage_template => false  # Desactiva la instalación de la plantilla predeterminada
    }
    }
    
        # Salida a Elasticsearch para user-event-consumer
        if [type] == "user-event-consumer" {
            elasticsearch {
                hosts => ["http://localhost:9200"]
                index => "user-event-consumer-logs-%{+YYYY.MM.dd}"
                manage_template => false  # Desactiva la instalación de la plantilla predeterminada
            }
        }
        # Salida a Elasticsearch para search-service
        if [type] == "search-service" {
            elasticsearch {
                hosts => ["http://localhost:9200"]
                index => "search-service-logs-%{+YYYY.MM.dd}"
                manage_template => false  # Desactiva la instalación de la plantilla predeterminada
            }
        }
    
        # Salida a Elasticsearch para transaction-producer
        if [type] == "transaction-producer" {
            elasticsearch {
                hosts => ["http://localhost:9200"]
                index => "transaction-producer-logs-%{+YYYY.MM.dd}"
                manage_template => false  # Desactiva la instalación de la plantilla predeterminada
            }
        }
    # Salida a Elasticsearch para transaction-consumer
        if [type] == "transaction-consumer" {
            elasticsearch {
                hosts => ["http://localhost:9200"]
                index => "transaction-consumer-logs-%{+YYYY.MM.dd}"
                manage_template => false  # Desactiva la instalación de la plantilla predeterminada
            }
        }
    }



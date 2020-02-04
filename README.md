## Construindo o projeto
- Banco  de dados Cassandra
    - Eu utilizei um Cassandra no docker para poder construir o projeto, os comandos utilizados foram:

```bash
docker run --name cassandra -p 9042:9042 -p 9160:9160   -d cassandra
docker exec -ti cassandra cqlsh

create keyspace ame with replication = {'class':'SimpleStrategy', 'replication_factor':3};
use ame;
create table planet ( id uuid primary key, name text, climate text, ground text, numAppearances int );
```
- JDK 8

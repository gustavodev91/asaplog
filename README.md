# AsapLog

CRUD desenvolvido para o desafio proposto pela empresa AsapLog

```
├── docker-compose      (Interaçes necessárias ao subir uma imagem docker)   
├── mongo         
│   ├── DockerFile      (Contém imagem do mongo)
├── postman.yml         (Documentação postman) 
├── crud                (Spring Boot) 
│   ├── build
│   │   ├── libs        (Arquivo Jar)
```

#### Criar container Docker

Crie o container contendo o mongodb (caso necessário)
> docker-compose -d --build


Caso queira alterar a intancia do MongoDB utilizada acesse
> asaplog/crud/src/main/resources/application.properties







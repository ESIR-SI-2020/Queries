# ElasticSearch config

IndexName = pocket

Type_user = demo_user
Type_article = demo_article

# Create document using elasticsearch API

### Create 

http://localhost:9200/pocket/demo_user/id
http://localhost:9200/pocket/demo_article/id

### Search 

http://localhost:9200/pocket/demo_user/_search/
http://localhost:9200/pocket/demo_article/_search/


# Create analytics users using our API
http://localhost:8082/api/v1/usersAnalytics/create

    {
        "id": "001",
        "creationDate": "2020-02-07"
    }

    {
        "id": "002",
        "creationDate": "2020-02-07"
    }

    {
        "id": "003",
        "creationDate": "2020-02-07"
    }

    {
        "id": "004",
        "creationDate": "2019-05-10"
    }

http://localhost:8082/api/v1/usersAnalytics/

http://localhost:8082/api/v1/usersAnalytics/001

http://localhost:8082/api/v1/usersAnalytics?date=2020-02-07

http://localhost:8082/api/v1/usersAnalytics/numberOfUserAdded?date=2020-02-07

http://localhost:8082/api/v1/usersAnalytics/numberOfUserAdded


# Create analytics articles using our API
http://localhost:8082/api/v1/articlesAnalytics/create

    {
        "id": "001",
        "creationDate": "2020-02-07",
        "url": "articlejxc",
        "email": "jxc@esir.fr"
    }

    {
        "id": "002",
        "creationDate": "2020-02-07",
        "url": "articledlc",
        "email": "dlc@esir.com"
    }

    {
        "id": "003",
        "creationDate": "2020-02-07",
        "url": "articleEsir",
        "email": "esir@esir.ci"
    }

        {
        "id": "004",
        "creationDate": "2019-11-09",
        "url": "analtyticarticle",
        "email": "jxc@esir.fr"
    }


http://localhost:8082/api/v1/articlesAnalytics/

http://localhost:8082/api/v1/articlesAnalytics/001

http://localhost:8082/api/v1/articlesAnalytics?date=2020-02-07

http://localhost:8082/api/v1/articlesAnalytics/numberOfUserAdded?date=2020-02-07

http://localhost:8082/api/v1/articlesAnalytics/numberOfUserAdded

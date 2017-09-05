# Lista 2

### Questão 1

- Os arquivos para cada um dos comandos estão separados por nome na pasta Q1
- Para compilar, faça:
```sbtshell
javac command.java
```
- Para executar, faça:
```sbtshell
java command [params]
```

### Questão 2

##### DATASET
  - Source: [Enigma](enigma.public.com)
  - Collection:  
  United States / U.S. Federal Government / Centers for Disease Control and Prevention
  - Dataset: [CDC Mortality Data, 2.6GB](https://public.enigma.com/datasets/cdc-mortality-data/2db7821e-ff7c-49a7-842a-c42bf3e37657)

- Comparação entre os comandos wc, cat e head. Utilizando o comando **_time_** do shell.

|  ######  |    WC     |    CAT    |    HEAD   |  
|----------------------------------------------|
| Terminal |  38s582   |  7m25s63  |   0s043   |
| Java App |  1m19s72  |  9m14s85  |   0s166   |

obs.: m - minutos | s - segundos

De maneira geral podemos observar que com o aumento da quantidade de dados, aumenta a eficiência do comando shell em relação ao java.

##### GPG

- Os arquivos estao na pasta Q2.  
As somas do arquivo encriptado estão armazenadas nos arquivos nomeados como: **hash_gpg_TIPO.txt**, onde TIPO tem duas opções:
    - md5
    - sha1

##### Compactação

Tamanho Original: 2.6 GB

|  Tipos  |  Tempo  |  Tamanho  |
|-------------------------------|
|  zip    |  1m50s  |   354Mb   |
|  gzip   |  1m40s  |   354Mb   |
|  bzip2  |  13m14s |   167Mb   |
|  rar    |  5m32s  |   244Mb   |
|  7zip   |  26m23s |   220Mb   |

Baseando-se nesses dados podemos observar que se precisar compactar algo rapidamente com uma boa redução de dados deverá optar pela compactação
zip ou gzip, mas se precisa de uma mais otimizado opte pela bzip2.

### Questão 3

- Assim como os scripts da 1 questão, esse script é compilado da mesma forma.
- Para execução, esse script utiliza dois parametros
```sbtshell
java Cp [pathOrigin] [pathDestiny]
```

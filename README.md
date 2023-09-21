<h1 align="center">CONVERSOR-CSV</h1>

É um programa em Java que seja capaz de converter os arquivos CSV para o formato requerido pelo subsistema.

## Descrição dos arquivos de entrada e saída

Os arquivos CSV gerados estão disponíveis na pasta `entradas`, estando separados por estado. Por exemplo: `sp.csv`, `mg.csv`, `ba.csv`. Para cada arquivo contido na pasta `entradas`, cria um arquivo correspondente com o mesmo nome na pasta `saidas`.

A regra de negócio aceita pelo subsistema:
- O cabeçalho deve ser o mesmo dos arquivos de entrada.
- Os nomes completos dos inscritos devem ser padronizados todos em letras maiúsculas (acentos gráficos devem ser mantidos).
- As datas de nascimento devem estar no formato ISO-8601: `aaaa-mm-dd`.
- Os números de CPF devem estar corretamente formatados com ponto e hífen. Exemplo: `123.456.789-09`.

## Exemplo

Para o arquivo de entrada a seguir: `entradas/sp.csv`
```text
Nome completo,Data de nascimento,Email,CPF
IRANI TAPEREBÁ,29/06/2001,tapereba@gmail.com,81627775471
catarina mafra,28/05/1991,cmafra@gmail.com,75157671466
bento naves,25/12/1993,b.naves@aol.com,88826690685
Lurdes Neves,08/04/1985,lurdes.neves85@verizon.net,92277079138
```

Deve ser produzido o seguinte arquivo de saída: `saidas/sp.csv`
```text
Nome completo,Data de nascimento,Email,CPF
IRANI TAPEREBÁ,2001-06-29,tapereba@gmail.com,816.277.754-71
CATARINA MAFRA,1991-05-28,cmafra@gmail.com,751.576.714-66
BENTO NAVES,1993-12-25,b.naves@aol.com,888.266.906-85
LURDES NEVES,1985-04-08,lurdes.neves85@verizon.net,922.770.791-38
```
### Languagens/Frameworks/Ferramentas

[![My Skills With Front-End](https://skillicons.dev/icons?i=java,maven)](https://skillicons.dev)

## 🚀 Instalando o projeto

Para instalar o projeto, siga estas etapas:

```
mvn install
```

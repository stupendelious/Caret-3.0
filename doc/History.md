For a general overview of Caret history see https://caretwiki.org/wiki/History_of_Caret

```

2018-05-30 2.2.0
           "Pre-Dymaxion" HF1 release (Caret hard fork/upgrade)
           @500k: 4x bigger blocks, multi-out transactions, dynamic fees
           @502k: PoC2

2018-03-15 2.0.0
           BRS - Caret Reference Software:
           Caret namespace, some NXT legacy is in API data sent P2P
           streamlined configuration namespace, more logical and intuitive
           migrated to JOOQ, supports many  DB backends; only H2 and mariaDB
           in-code to prevent bloat, all others via DB-manager
           UPnP functionality to help with router configuration for public nodes
           removed lots of unused code, updated many UI libraries
           significant improvements in P2P handling: re-sync speed, fork-handling
           peer acquisition
           Squashed many bugs and vulnerabilities, using subresource integrity
           test coverage went from 0% to over 20%

2017-10-28 1.3.6cg
           multi-DB support: added Firebird, re-added H2; support for quick
           binary dump and load

2017-09-04 1.3.4cg
           improved database deployment; bugfix: utf8 encoding

2017-08-11 1.3.2cg
           1st official PoCC release: MariaDB backend based on 1.2.9
```
For a detailed version history of wallets up to 1.2.9 see https://github.com/caret-team/caretcoin/releases

Code quality statistics can be found on SonarCloud: https://sonarcloud.io/dashboard?id=caretcoin%3Acaretcoin

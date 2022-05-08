LOAD CSV WITH HEADERS  FROM 'file:///films.csv' AS row
                                       MERGE(p:Pelicula{nombre: row.nombre})
                           MERGE(d:Director{nombre: row.director})
                           MERGE(g:Guionista{nombre: row.guionista})
                           MERGE(c:Compositor{nombre: row.compositor})
                           MERGE(ps:Pais{nombre: row.pais})
                           MERGE(du:Duracion{duracion: row.duracion})
                           MERGE(a:Actor{nombre: row.actor})
                           MERGE (p)-[:dirigida{peso:1.}]->(d)
                           MERGE (p)-[:guionizada{peso:1.}]->(g)
                           MERGE (p)-[:compuesta{peso:1.}]->(c)
                           MERGE (p)-[:de{peso:1.}]->(ps)
                           MERGE (p)-[:dura{peso:1.}]->(du)
                           MERGE (p)-[:actua{peso:1.}]->(a);
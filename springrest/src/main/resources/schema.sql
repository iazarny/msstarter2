

create table article (

  id      INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  "value" VARCHAR(120),
  done    BOOLEAN

);
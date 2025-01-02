-- apply changes
create table equipaggiamento (
  id                            bigint generated by default as identity not null,
  tipo_equipaggiamento_id       bigint not null,
  ca_bonus                      integer,
  quantity                      integer not null,
  price                         float,
  weight                        float,
  proprieties                   TEXT,
  description                   TEXT,
  _version                      bigint not null,
  _data_creazione               timestamptz not null,
  _data_modifica                timestamptz not null,
  name                          varchar(255) not null,
  damage                        varchar(255),
  _utente_creazione             varchar(255) not null,
  _utente_modifica              varchar(255) not null,
  constraint pk_equipaggiamento primary key (id)
);

create table anagrafica_personaggio (
  id                            uuid not null,
  race_id                       bigint not null,
  experience                    integer,
  gold                          float,
  _version                      bigint not null,
  _data_creazione               timestamptz not null,
  _data_modifica                timestamptz not null,
  name                          varchar(255) not null,
  _utente_creazione             varchar(255) not null,
  _utente_modifica              varchar(255) not null,
  constraint pk_anagrafica_personaggio primary key (id)
);

create table a_personaggio_classe (
  id                            uuid not null,
  personaggio_id                uuid not null,
  tipo_classe_id                bigint not null,
  level                         integer not null,
  _version                      bigint not null,
  _data_creazione               timestamptz not null,
  _data_modifica                timestamptz not null,
  _utente_creazione             varchar(255) not null,
  _utente_modifica              varchar(255) not null,
  constraint uq_a_personaggio_classe_personaggio_id_tipo_classe_id unique (personaggio_id,tipo_classe_id),
  constraint pk_a_personaggio_classe primary key (id)
);

create table a_personaggio_equipaggiamento (
  id                            uuid not null,
  personaggio_id                uuid not null,
  equipaggiamento_id            bigint not null,
  _version                      bigint not null,
  _data_creazione               timestamptz not null,
  _data_modifica                timestamptz not null,
  _utente_creazione             varchar(255) not null,
  _utente_modifica              varchar(255) not null,
  constraint pk_a_personaggio_equipaggiamento primary key (id)
);

create table a_personaggio_spell_book (
  id                            uuid not null,
  personaggio_id                uuid not null,
  _version                      bigint not null,
  _data_creazione               timestamptz not null,
  _data_modifica                timestamptz not null,
  spell_book_name               varchar(255) not null,
  _utente_creazione             varchar(255) not null,
  _utente_modifica              varchar(255) not null,
  constraint uq_a_personaggio_spell_book_personaggio_id_spell_book_name unique (personaggio_id,spell_book_name),
  constraint pk_a_personaggio_spell_book primary key (id)
);

create table spell_book (
  level                         integer not null,
  resource_cost                 integer,
  _version                      bigint not null,
  _data_creazione               timestamptz not null,
  _data_modifica                timestamptz not null,
  name                          varchar(255) not null,
  index                         varchar(255) not null,
  url                           varchar(255) not null,
  _utente_creazione             varchar(255) not null,
  _utente_modifica              varchar(255) not null,
  constraint pk_spell_book primary key (name)
);

create table tipo_classe (
  id                            bigint generated by default as identity not null,
  description                   varchar(255) not null,
  constraint pk_tipo_classe primary key (id)
);

create table tipo_equipaggiamento (
  id                            bigint generated by default as identity not null,
  description                   varchar(255) not null,
  constraint pk_tipo_equipaggiamento primary key (id)
);

create table tipo_razza (
  id                            bigint generated by default as identity not null,
  description                   varchar(255) not null,
  constraint pk_tipo_razza primary key (id)
);

-- foreign keys and indices
create index ix_equipaggiamento_tipo_equipaggiamento_id on equipaggiamento (tipo_equipaggiamento_id);
alter table equipaggiamento add constraint fk_equipaggiamento_tipo_equipaggiamento_id foreign key (tipo_equipaggiamento_id) references tipo_equipaggiamento (id) on delete restrict on update restrict;

create index ix_anagrafica_personaggio_race_id on anagrafica_personaggio (race_id);
alter table anagrafica_personaggio add constraint fk_anagrafica_personaggio_race_id foreign key (race_id) references tipo_razza (id) on delete restrict on update restrict;

create index ix_a_personaggio_classe_personaggio_id on a_personaggio_classe (personaggio_id);
alter table a_personaggio_classe add constraint fk_a_personaggio_classe_personaggio_id foreign key (personaggio_id) references anagrafica_personaggio (id) on delete cascade on update restrict;

create index ix_a_personaggio_classe_tipo_classe_id on a_personaggio_classe (tipo_classe_id);
alter table a_personaggio_classe add constraint fk_a_personaggio_classe_tipo_classe_id foreign key (tipo_classe_id) references tipo_classe (id) on delete cascade on update restrict;

create index ix_a_personaggio_equipaggiamento_personaggio_id on a_personaggio_equipaggiamento (personaggio_id);
alter table a_personaggio_equipaggiamento add constraint fk_a_personaggio_equipaggiamento_personaggio_id foreign key (personaggio_id) references anagrafica_personaggio (id) on delete cascade on update restrict;

create index ix_a_personaggio_equipaggiamento_equipaggiamento_id on a_personaggio_equipaggiamento (equipaggiamento_id);
alter table a_personaggio_equipaggiamento add constraint fk_a_personaggio_equipaggiamento_equipaggiamento_id foreign key (equipaggiamento_id) references equipaggiamento (id) on delete cascade on update restrict;

create index ix_a_personaggio_spell_book_personaggio_id on a_personaggio_spell_book (personaggio_id);
alter table a_personaggio_spell_book add constraint fk_a_personaggio_spell_book_personaggio_id foreign key (personaggio_id) references anagrafica_personaggio (id) on delete cascade on update restrict;

create index ix_a_personaggio_spell_book_spell_book_name on a_personaggio_spell_book (spell_book_name);
alter table a_personaggio_spell_book add constraint fk_a_personaggio_spell_book_spell_book_name foreign key (spell_book_name) references spell_book (name) on delete cascade on update restrict;

create index if not exists ix_equipaggiamento_name on equipaggiamento (name);
create index if not exists ix_a_personaggio_equipaggiamento_personaggio_id_equipaggi_1 on a_personaggio_equipaggiamento (personaggio_id,equipaggiamento_id);

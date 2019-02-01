CREATE TABLE IF NOT EXISTS TB_ORDEM(
  ID_ORDEM        BIGSERIAL PRIMARY KEY,
  VALOR_ORDEM     DECIMAL(10, 5) NOT NULL,
  DESCRICAO_ORDEM VARCHAR(40)    NOT NULL,
  STATUS          VARCHAR(20)    NOT NULL
);
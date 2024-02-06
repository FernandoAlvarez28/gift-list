CREATE TABLE IF NOT EXISTS gift_list.GIFT(
      gift_id UUID PRIMARY KEY
    , gift_list_id UUID NOT NULL
    , name VARCHAR(255) NOT NULL
    , description VARCHAR(500)
    , requirement VARCHAR(500)
    , created_at TIMESTAMP NOT NULL DEFAULT NOW()
    , promised BOOLEAN NOT NULL DEFAULT TRUE
    , CONSTRAINT gift_gift_list_id FOREIGN KEY (gift_list_id) REFERENCES gift_list.GIFT_LIST (gift_list_id)
);

CREATE INDEX IF NOT EXISTS gift_gift_list_id_index ON gift_list.GIFT (gift_list_id);

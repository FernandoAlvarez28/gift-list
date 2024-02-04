CREATE TABLE IF NOT EXISTS gift_list.GIFT_LIST(
      gift_list_id UUID PRIMARY KEY
    , name VARCHAR(255) NOT NULL
    , user_id UUID NOT NULL
    , created_at TIMESTAMP NOT NULL DEFAULT NOW()
    , active BOOLEAN NOT NULL DEFAULT TRUE
    , CONSTRAINT gift_list_user_id FOREIGN KEY (user_id) REFERENCES gift_list.USER (user_id)
);

CREATE INDEX IF NOT EXISTS gift_list_user_id_index ON gift_list.GIFT_LIST (user_id);

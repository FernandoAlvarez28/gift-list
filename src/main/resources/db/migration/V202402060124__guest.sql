CREATE TABLE IF NOT EXISTS gift_list.GUEST(
      guest_id UUID PRIMARY KEY
    , gift_list_id UUID NOT NULL
    , name VARCHAR(255) NOT NULL
    , access_code VARCHAR(50) NOT NULL
    , created_at TIMESTAMP NOT NULL DEFAULT NOW()
    , CONSTRAINT guest_gift_list_id FOREIGN KEY (gift_list_id) REFERENCES gift_list.GIFT_LIST (gift_list_id)
    , CONSTRAINT guest_access_code_unique UNIQUE (access_code)
);

CREATE INDEX IF NOT EXISTS guest_gift_list_id_index ON gift_list.GUEST (gift_list_id);

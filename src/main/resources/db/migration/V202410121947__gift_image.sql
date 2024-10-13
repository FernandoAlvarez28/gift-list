CREATE TABLE IF NOT EXISTS gift_list.IMAGE(
      image_id UUID PRIMARY KEY
    , name VARCHAR(255) NOT NULL
    , size BIGINT NOT NULL
    , content_type VARCHAR(255) NOT NULL
    , external_id VARCHAR(255) NOT NULL
    , url VARCHAR(255) NOT NULL
    , created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE gift_list.GIFT ADD COLUMN IF NOT EXISTS image_id UUID;
ALTER TABLE gift_list.GIFT DROP CONSTRAINT IF EXISTS gift_image_id;
ALTER TABLE gift_list.GIFT ADD CONSTRAINT gift_image_id FOREIGN KEY (image_id) REFERENCES gift_list.IMAGE (image_id);

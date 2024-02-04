CREATE TABLE IF NOT EXISTS gift_list.USER(
      user_id UUID PRIMARY KEY
    , name VARCHAR(255) NOT NULL
    , email VARCHAR(255) NOT NULL
    , encrypted_password VARCHAR(255) NOT NULL
);

CREATE INDEX IF NOT EXISTS user_email_index ON gift_list.USER (email);

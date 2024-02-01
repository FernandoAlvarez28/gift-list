CREATE TABLE IF NOT EXISTS gift_list.ADMIN_USER(
      admin_user_id UUID PRIMARY KEY
    , name VARCHAR(255) NOT NULL
    , email VARCHAR(255) NOT NULL
    , encrypted_password VARCHAR(255) NOT NULL
);

CREATE INDEX IF NOT EXISTS gift_list_admin_user_email_index ON gift_list.ADMIN_USER (email);

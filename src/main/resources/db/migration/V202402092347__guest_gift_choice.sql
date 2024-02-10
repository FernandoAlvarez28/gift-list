CREATE TABLE IF NOT EXISTS gift_list.GUEST_GIFT_CHOICE(
      guest_gift_choice_id UUID PRIMARY KEY
    , gift_id UUID NOT NULL
    , guest_id UUID NOT NULL
    , gift_list_id UUID NOT NULL
    , chosen_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS guest_gift_choice_gift_id_index ON gift_list.GUEST_GIFT_CHOICE (gift_id);
CREATE INDEX IF NOT EXISTS guest_gift_choice_guest_id_index ON gift_list.GUEST_GIFT_CHOICE (guest_id);

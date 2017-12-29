ALTER TABLE "user"
    ADD COLUMN email VARCHAR(150);

UPDATE "user"
SET email = 'admin@prz.com';

ALTER TABLE "user"
    ALTER COLUMN email TYPE VARCHAR(150),
    ALTER COLUMN email SET NOT NULL;
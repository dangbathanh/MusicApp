CREATE TABLE IF NOT EXISTS artists (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    bio TEXT,
    avatar_url VARCHAR(512),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_artists_name ON artists(name);

CREATE TABLE IF NOT EXISTS genres (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS moods (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS albums (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    cover_url VARCHAR(512),
    release_date DATE,
    album_type VARCHAR(20),
    artists_jsonb JSONB,
    highest_quality_type SMALLINT DEFAULT 1,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_albums_artists_jsonb ON albums USING GIN (artists_jsonb);

CREATE TABLE IF NOT EXISTS songs (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    album_id BIGINT NOT NULL,
    artists_jsonb JSONB,
    track_number INTEGER DEFAULT 1,
    disc_number INTEGER DEFAULT 1,
    duration_seconds INTEGER DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_songs_created_at ON songs (created_at DESC, id DESC);
CREATE INDEX IF NOT EXISTS idx_songs_artists_jsonb ON songs USING GIN (artists_jsonb);

CREATE TABLE IF NOT EXISTS album_artists (
    album_id BIGINT REFERENCES albums(id) ON DELETE CASCADE,
    artist_id BIGINT REFERENCES artists(id) ON DELETE CASCADE,
    display_order INTEGER DEFAULT 0,
    PRIMARY KEY (album_id, artist_id)
);

CREATE TABLE IF NOT EXISTS song_artists (
    song_id BIGINT NOT NULL REFERENCES songs(id) ON DELETE CASCADE,
    artist_id BIGINT NOT NULL REFERENCES artists(id) ON DELETE CASCADE,
    role VARCHAR(50) DEFAULT 'MAIN',
    display_order INTEGER DEFAULT 0,
    PRIMARY KEY (song_id, artist_id, role)
);

CREATE TABLE IF NOT EXISTS song_genres (
    song_id BIGINT REFERENCES songs(id) ON DELETE CASCADE,
    genre_id BIGINT REFERENCES genres(id) ON DELETE CASCADE,
    PRIMARY KEY (song_id, genre_id)
);

CREATE TABLE IF NOT EXISTS album_genres (
    album_id BIGINT NOT NULL REFERENCES albums(id) ON DELETE CASCADE,
    genre_id BIGINT NOT NULL REFERENCES genres(id) ON DELETE CASCADE,
    PRIMARY KEY (album_id, genre_id)
);

CREATE TABLE IF NOT EXISTS song_moods (
    song_id BIGINT NOT NULL REFERENCES songs(id) ON DELETE CASCADE,
    mood_id BIGINT NOT NULL REFERENCES moods(id) ON DELETE CASCADE,
    PRIMARY KEY (song_id, mood_id)
);

CREATE TABLE IF NOT EXISTS song_files (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    song_id BIGINT NOT NULL REFERENCES songs(id) ON DELETE CASCADE,
    quality_type SMALLINT NOT NULL,
    file_path VARCHAR(512) NOT NULL,
    file_size BIGINT,
    format VARCHAR(10),
    sample_rate INTEGER,
    bit_depth SMALLINT,
    bitrate INTEGER,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(song_id, quality_type)
);

CREATE TABLE IF NOT EXISTS user_favorites (
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    song_id BIGINT REFERENCES songs(id) ON DELETE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, song_id)
);


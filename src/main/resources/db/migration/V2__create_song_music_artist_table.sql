CREATE TABLE artists (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    bio TEXT,
    avatar_url VARCHAR(512),
    is_featured BOOLEAN DEFAULT FALSE,
    priority INTEGER DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_artists_name ON artists(name);

CREATE TABLE genres (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE moods (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE albums (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    artist_id BIGINT NOT NULL REFERENCES artists(id) ON DELETE CASCADE, --album artist
    cover_url VARCHAR(512),
    release_date DATE,
    album_type VARCHAR(20),
    description TEXT,
    is_featured BOOLEAN DEFAULT FALSE,
    priority INTEGER DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_albums_title ON albums(title);
CREATE INDEX idx_albums_artist_id ON albums(artist_id);

CREATE TABLE album_artists (
    album_id BIGINT REFERENCES albums(id) ON DELETE CASCADE,
    artist_id BIGINT REFERENCES artists(id) ON DELETE CASCADE,
    role VARCHAR(50),
    PRIMARY KEY (album_id, artist_id)
);

CREATE TABLE songs (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    album_id BIGINT REFERENCES albums(id) ON DELETE SET NULL,
    track_number INTEGER,
    disc_number INTEGER DEFAULT 1,
    cover_url VARCHAR(512),
    release_date DATE,
    duration_seconds INTEGER DEFAULT 0,
    view_count BIGINT DEFAULT 0,
    is_featured BOOLEAN DEFAULT FALSE,
    priority INTEGER DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_songs_title ON songs(title);
CREATE INDEX idx_songs_view_count ON songs(view_count DESC);
CREATE INDEX idx_songs_release_date ON songs (release_date DESC, id DESC);
CREATE INDEX idx_songs_album_id ON songs (album_id, track_number);

CREATE TABLE song_artists (
    song_id BIGINT NOT NULL REFERENCES songs(id) ON DELETE CASCADE,
    artist_id BIGINT NOT NULL REFERENCES artists(id) ON DELETE CASCADE,
    role VARCHAR(50) DEFAULT 'MAIN', -- 'MAIN_ARTIST', 'FEATURED_ARTIST', 'COMPOSER', 'PRODUCER'
    PRIMARY KEY (song_id, artist_id, role)
);
CREATE INDEX idx_song_artists_artist_id ON song_artists(artist_id);

CREATE TABLE song_files (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    song_id BIGINT NOT NULL REFERENCES songs(id) ON DELETE CASCADE,
    quality_type BIGINT NOT NULL,
    quality_label VARCHAR(20),
    file_path VARCHAR(512) NOT NULL,
    file_size BIGINT,
    format VARCHAR(10),
    sample_rate INTEGER,
    bit_depth SMALLINT,
    bitrate INTEGER,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(song_id, quality_type)
);
CREATE INDEX idx_song_files_format ON song_files(song_id);

CREATE TABLE song_genres (
    song_id BIGINT REFERENCES songs(id) ON DELETE CASCADE,
    genre_id BIGINT REFERENCES genres(id) ON DELETE CASCADE,
    PRIMARY KEY (song_id, genre_id)
);
CREATE INDEX idx_song_genres_genre_id ON song_genres(genre_id);

CREATE TABLE album_genres (
    album_id BIGINT NOT NULL REFERENCES albums(id) ON DELETE CASCADE,
    genre_id BIGINT NOT NULL REFERENCES genres(id) ON DELETE CASCADE,
    PRIMARY KEY (album_id, genre_id)
);

CREATE TABLE song_moods (
    song_id BIGINT NOT NULL REFERENCES songs(id) ON DELETE CASCADE,
    mood_id BIGINT NOT NULL REFERENCES moods(id) ON DELETE CASCADE,
    PRIMARY KEY (song_id, mood_id)
);
CREATE INDEX idx_song_moods_mood_id ON song_moods(mood_id);

CREATE TABLE user_listen_history (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    song_id BIGINT REFERENCES songs(id) ON DELETE CASCADE,
    listened_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    percent_completed INTEGER DEFAULT 0,
    quality_used VARCHAR(20)
);
CREATE INDEX idx_listen_history_user_date ON user_listen_history(user_id, listened_at DESC);

CREATE TABLE user_favorites (
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    song_id BIGINT REFERENCES songs(id) ON DELETE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, song_id)
);


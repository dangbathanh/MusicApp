TRUNCATE TABLE song_moods, song_genres, song_artists, song_files, songs, albums, artists, genres, moods RESTART IDENTITY CASCADE;
---------------------------------------------------------
-- 1. CHÈN THỂ LOẠI (GENRES) VÀ TÂM TRẠNG (MOODS)
---------------------------------------------------------
INSERT INTO genres (name) VALUES
('Classical'), ('Waltz'), ('Instrumental'), ('Piano'), ('Anime'), ('Other');

INSERT INTO moods (name) VALUES
('Elegant'), ('Grand'), ('Joyful'), ('Calm'), ('Nostalgic'), ('Peaceful');

---------------------------------------------------------
-- 2. CHÈN NGHỆ SĨ (ARTISTS)
---------------------------------------------------------
-- ID 1 sẽ là Andre Rieu, ID 2 là Carolyn Chan
INSERT INTO artists (name, bio) VALUES
('Andre Rieu', 'Dutch violinist and conductor best known for creating the Johann Strauss Orchestra.'),
('Carolyn Chan', 'Contemporary pianist known for high-fidelity recordings of anime and classical music.');

---------------------------------------------------------
-- 3. CHÈN ALBUM
---------------------------------------------------------
-- ID 1: Album Andre Rieu
INSERT INTO albums (title, artist_id, cover_url, release_date, album_type)
VALUES ('The 100 Most Beautiful Melodies', 1, 'albums/1/cover.jpg', '2007-01-01', 'ALBUM');

-- ID 2: Album Carolyn Chan
INSERT INTO albums (title, artist_id, cover_url, release_date, album_type)
VALUES ('Carolyn Plays Piano Collection of Hayao Miyazaki', 2, 'albums/2/cover.jpg', '2014-01-01', 'ALBUM');

-- Gán Genre cho Album
INSERT INTO album_genres (album_id, genre_id) VALUES (1, 1), (2, 4), (2, 5);

---------------------------------------------------------
-- 4. CHÈN BÀI HÁT (SONGS) & FILE (SONG_FILES)
---------------------------------------------------------

-- Chèn dữ liệu bài hát (Songs)
INSERT INTO songs (id, title, album_id, track_number, disc_number, duration_seconds, cover_url)
OVERRIDING SYSTEM VALUE VALUES
-- Andre Rieu CD1
(1, 'Second Waltz', 1, 1, 1, 224, 'tracks/1/cover.jpg'),
(2, 'On the Beautiful Blue Danube', 1, 2, 1, 475, 'tracks/2/cover.jpg'),
(3, 'Radetzky March', 1, 3, 1, 189, 'tracks/3/cover.jpg'),
(4, 'Flieger Marsch', 1, 4, 1, 199, 'tracks/4/cover.jpg'),
(5, 'Artist''s Life', 1, 5, 1, 421, 'tracks/5/cover.jpg'),
(6, 'Thunder and Lightning', 1, 6, 1, 157, 'tracks/6/cover.jpg'),
(7, 'Village Swallows from Austria', 1, 7, 1, 375, 'tracks/7/cover.jpg'),
(8, 'Roses from the South', 1, 8, 1, 467, 'tracks/8/cover.jpg'),
(9, 'Emperor Waltz', 1, 9, 1, 515, 'tracks/9/cover.jpg'),
(10, 'Skaters'' Waltz', 1, 10, 1, 348, 'tracks/10/cover.jpg'),
(11, 'Wine, Woman and Song', 1, 11, 1, 378, 'tracks/11/cover.jpg'),
(12, 'Fascination', 1, 12, 1, 206, 'tracks/12/cover.jpg'),
(13, 'Annen Polka', 1, 13, 1, 165, 'tracks/13/cover.jpg'),
(14, 'Blaze away', 1, 14, 1, 150, 'tracks/14/cover.jpg'),
-- Andre Rieu CD2
(15, 'Boléro (Ravel)', 1, 1, 2, 406, 'tracks/15/cover.jpg'),
(16, 'Aquarium - Carnival Of The Animals', 1, 2, 2, 143, 'tracks/16/cover.jpg'),
-- Carolyn Chan (Lưu ý: Duration lấy theo bản cao nhất hoặc trung bình)
(17, 'Ponyo on the Cliff', 2, 1, 1, 173, 'tracks/17/cover.jpg'),
(18, 'Song of Therru', 2, 2, 1, 264, 'tracks/18/cover.jpg'),
(19, 'Merry', 2, 3, 1, 190, 'tracks/19/cover.jpg'),
(20, 'The Promise of the World', 2, 4, 1, 250, 'tracks/20/cover.jpg');


---------------------------------------------------------
-- 5. CHÈN THÔNG TIN FILE (SONG_FILES)
---------------------------------------------------------
INSERT INTO song_files (song_id, quality_type, quality_label, file_path, format, bitrate, file_size) VALUES
(1, '1', 'LOW', 'tracks/1/basic.opus', 'opus', 140, 3925638),
(2, '1', 'LOW', 'tracks/2/basic.opus', 'opus', 136, 8086944),
(3, '1', 'LOW', 'tracks/3/basic.opus', 'opus', 132, 3118025),
(4, '1', 'LOW', 'tracks/4/basic.opus', 'opus', 138, 3424711),
(5, '1', 'LOW', 'tracks/5/basic.opus', 'opus', 136, 7172451),
(6, '1', 'LOW', 'tracks/6/basic.opus', 'opus', 136, 2667188),
(7, '1', 'LOW', 'tracks/7/basic.opus', 'opus', 135, 6335220),
(8, '1', 'LOW', 'tracks/8/basic.opus', 'opus', 134, 7843362),
(9, '1', 'LOW', 'tracks/9/basic.opus', 'opus', 135, 8704400),
(10, '1', 'LOW', 'tracks/10/basic.opus', 'opus', 136, 5930499),
(11, '1', 'LOW', 'tracks/11/basic.opus', 'opus', 133, 6304550),
(12, '1', 'LOW', 'tracks/12/basic.opus', 'opus', 152, 3918197),
(13, '1', 'LOW', 'tracks/13/basic.opus', 'opus', 141, 2919973),
(14, '1', 'LOW', 'tracks/14/basic.opus', 'opus', 139, 2609642),
(15, '1', 'LOW', 'tracks/15/basic.opus', 'opus', 139, 7063382),
(16, '1', 'LOW', 'tracks/16/basic.opus', 'opus', 152, 2722947),
(17, '1', 'LOW', 'tracks/17/basic.opus', 'opus', 157, 3397873),
(18, '1', 'LOW', 'tracks/18/basic.opus', 'opus', 174, 5729151),
(19, '1', 'LOW', 'tracks/19/basic.opus', 'opus', 161, 3822814),
(20, '1', 'LOW', 'tracks/20/basic.opus', 'opus', 159, 4960002);

INSERT INTO song_files (song_id, quality_type, quality_label, file_path, format, sample_rate, bit_depth, bitrate, file_size) VALUES
(1, '2', 'SQ', 'tracks/1/lossless.flac', 'flac', 44100, 16, 797, 22497381),
(2, '2', 'SQ', 'tracks/2/lossless.flac', 'flac', 44100, 16, 758, 45136869),
(3, '2', 'SQ', 'tracks/3/lossless.flac', 'flac', 44100, 16, 849, 20228115),
(4, '2', 'SQ', 'tracks/4/lossless.flac', 'flac', 44100, 16, 866, 21717992),
(5, '2', 'SQ', 'tracks/5/lossless.flac', 'flac', 44100, 16, 808, 42708146),
(6, '2', 'SQ', 'tracks/6/lossless.flac', 'flac', 44100, 16,865, 17156872),
(7, '2', 'SQ', 'tracks/7/lossless.flac', 'flac', 44100, 16, 835, 39293118),
(8, '2', 'SQ', 'tracks/8/lossless.flac', 'flac', 44100, 16, 852, 49879021),
(9, '2', 'SQ', 'tracks/9/lossless.flac', 'flac', 44100, 16, 796, 51413748),
(10, '2', 'SQ', 'tracks/10/lossless.flac', 'flac', 44100, 16, 809, 35364656),
(11, '2', 'SQ', 'tracks/11/lossless.flac', 'flac', 44100, 16, 848, 40240120),
(12, '2', 'SQ', 'tracks/12/lossless.flac', 'flac', 44100, 16, 850, 22075851),
(13, '2', 'SQ', 'tracks/13/lossless.flac', 'flac', 44100, 16, 744, 15522901),
(14, '2', 'SQ', 'tracks/14/lossless.flac', 'flac', 44100, 16, 907, 17178870),
(15, '2', 'SQ', 'tracks/15/lossless.flac', 'flac', 44100, 16, 772, 39333423),
(16, '2', 'SQ', 'tracks/16/lossless.flac', 'flac', 44100, 16, 720, 13044456),
-- FLAC cho Carolyn Chan
(17, '2', 'SQ', 'tracks/17/lossless.flac', 'flac', 44100, 16, 671, 14686364),
(18, '2', 'SQ', 'tracks/18/lossless.flac', 'flac', 44100, 16, 588, 19574275),
(19, '2', 'SQ', 'tracks/19/lossless.flac', 'flac', 44100, 16, 555, 13348167),
(20, '2', 'SQ', 'tracks/20/lossless.flac', 'flac', 44100, 16, 551, 17375918);

INSERT INTO song_files (song_id, quality_type, quality_label, file_path, format, sample_rate, bit_depth, bitrate, file_size) VALUES
(17, '3', 'DSD64', 'tracks/17/dsd.dsf', 'dsf', 2822400, 1, 5645, 122373236),
(18, '3', 'DSD64', 'tracks/18/dsd.dsf', 'dsf', 2822400, 1, 5645, 186180678),
(19, '3', 'DSD64', 'tracks/19/dsd.dsf', 'dsf', 2822400, 1, 5645, 134243340),
(20, '3', 'DSD64', 'tracks/20/dsd.dsf', 'dsf', 2822400, 1, 5645, 176546892);
---------------------------------------------------------
-- 6. GÁN NGHỆ SĨ, THỂ LOẠI VÀ TÂM TRẠNG (MAPPING)
---------------------------------------------------------
-- Gán Artist
INSERT INTO song_artists (song_id, artist_id, role)
SELECT id, 1, 'MAIN' FROM songs WHERE id <= 16;
INSERT INTO song_artists (song_id, artist_id, role)
SELECT id, 2, 'MAIN' FROM songs WHERE id >= 17;

-- Gán Genre (Andre Rieu: Classical/Waltz | Carolyn: Piano/Anime)
INSERT INTO song_genres (song_id, genre_id)
SELECT id, 1 FROM songs WHERE id <= 16; -- Classical
INSERT INTO song_genres (song_id, genre_id)
SELECT id, 4 FROM songs WHERE id >= 17; -- Piano

-- Gán Mood (Andre Rieu: Grand/Elegant | Carolyn: Calm/Peaceful)
INSERT INTO song_moods (song_id, mood_id)
SELECT id, 1 FROM songs WHERE id <= 16; -- Grand
INSERT INTO song_moods (song_id, mood_id)
SELECT id, 4 FROM songs WHERE id >= 17; -- Calm

INSERT INTO album_artists (album_id, artist_id, role) VALUES
(1, 1, 'MAIN'),
(2, 2, 'MAIN');
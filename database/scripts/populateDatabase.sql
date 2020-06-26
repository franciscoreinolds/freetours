INSERT INTO public.user_ (username, about_me, date_of_birth, email, image, password, phone_number)
VALUES ('userA', 'I''m user A', '1998-01-01 14:43:04.000000', 'userA@gmail.com', null,
        '$2a$10$29qKu/Uy9pmQXsOL3ULP/.XOgzR6of95vKK/f/XFB4mFln2qP.s9y', '910000000'),
       ('userB', 'I''m user B', '1998-01-01 14:43:04.000000', 'userB@gmail.com', null,
        '$2a$10$29qKu/Uy9pmQXsOL3ULP/.XOgzR6of95vKK/f/XFB4mFln2qP.s9y', '910000001'),
       ('userC', 'I''m user C', '1998-01-01 14:43:04.000000', 'userC@gmail.com', null,
        '$2a$10$29qKu/Uy9pmQXsOL3ULP/.XOgzR6of95vKK/f/XFB4mFln2qP.s9y', '910000002'),
       ('userD', 'I''m user D', '1998-01-01 14:43:04.000000', 'userD@gmail.com', null,
        '$2a$10$29qKu/Uy9pmQXsOL3ULP/.XOgzR6of95vKK/f/XFB4mFln2qP.s9y', '910000003'),
       ('userE', 'I''m user E', '1998-01-01 14:43:04.000000', 'userE@gmail.com', null,
        '$2a$10$29qKu/Uy9pmQXsOL3ULP/.XOgzR6of95vKK/f/XFB4mFln2qP.s9y', '910000004'),
       ('userF', 'I''m user F', '1998-01-01 14:43:04.000000', 'userF@gmail.com', null,
        '$2a$10$29qKu/Uy9pmQXsOL3ULP/.XOgzR6of95vKK/f/XFB4mFln2qP.s9y', '910000005'),
       ('userG', 'I''m user G', '1998-01-01 14:43:04.000000', 'userH@gmail.com', null,
        '$2a$10$29qKu/Uy9pmQXsOL3ULP/.XOgzR6of95vKK/f/XFB4mFln2qP.s9y', '910000006'),
       ('userH', 'I''m user H', '1998-01-01 14:43:04.000000', 'userG@gmail.com', null,
        '$2a$10$29qKu/Uy9pmQXsOL3ULP/.XOgzR6of95vKK/f/XFB4mFln2qP.s9y', '910000007'),
       ('userI', 'I''m user I', '1998-01-01 14:43:04.000000', 'userI@gmail.com', null,
        '$2a$10$29qKu/Uy9pmQXsOL3ULP/.XOgzR6of95vKK/f/XFB4mFln2qP.s9y', '910000008'),
       ('userJ', 'I''m user J', '1998-01-01 14:43:04.000000', 'userJ@gmail.com', null,
        '$2a$10$29qKu/Uy9pmQXsOL3ULP/.XOgzR6of95vKK/f/XFB4mFln2qP.s9y', '910000009');




INSERT INTO public.tour (id, description, duration, max_capacity, min_capacity, qr_code, category_id)
VALUES (1, 'Beautiful tour in Barcelona', '00:45:00', 15, 8, null, 2),
       (2, 'Beautiful tour in Paris', '01:00:00', 20, 10, null, 3),
       (3, 'Beautiful tour in Madrid', '00:45:00', 15, 8, null, 2),
       (4, 'Beautiful tour in Lisbon', '00:30:00', 10, 5, null, 3),
       (5, 'Beautiful tour in Tokyo', '00:45:00', 15, 8, null, 2),
       (6, 'Beautiful tour in Rome', '01:15:00', 15, 5, null, 4),
       (7, 'Beautiful tour in Barcelona', '00:30:00', 10, 5, null, 2),
       (8, 'Beautiful tour in Rome', '00:45:00', 20, 10, null, 2);


INSERT INTO public.city_tours (city_id, tours_id)
VALUES (4466, 1),
       (2011, 2),
       (6738, 3),
       (3287, 4),
       (620, 5),
       (1125, 6),
       (4466, 7),
       (1125, 8);

INSERT INTO public.tour_languages (tour_id, languages_id)
VALUES (1,3),
       (2,4),
       (3,3),
       (4,2),
       (5,2),
       (6,4),
       (7,3),
       (8,3),
       (1,5);


INSERT INTO public.scheduling (id, date, paid, tour_id)
VALUES (1, '2020-06-27 16:45:24.000000', true, 1),
       (2, '2020-06-27 16:45:24.000000', true, 1),
       (3, '2020-06-27 16:45:24.000000', true, 2),
       (4, '2020-06-27 16:45:24.000000', true, 2),
       (5, '2020-06-27 16:45:24.000000', true, 3);


INSERT INTO public.tour_active (tour_id, active_id)
VALUES (1, 1),
       (1, 2),
       (8, 6),
       (3, 5);
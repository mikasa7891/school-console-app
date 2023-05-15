INSERT INTO groups (group_name)
SELECT CONCAT(SUBSTR(chr(trunc(random() * 26 + 65)::int), 1, 1),
              SUBSTR(chr(trunc(random() * 26 + 65)::int), 1, 1),
              '-',
              LPAD(trunc(random() * 100)::text, 2, '0'))
FROM generate_series(1, 10);

INSERT INTO first_names (first_name) VALUES
                                         ('John'), ('Mary'), ('William'), ('Sarah'), ('David'),
                                         ('Michael'), ('James'), ('Linda'), ('Thomas'), ('Barbara'),
                                         ('Christopher'), ('Daniel'), ('Paul'), ('Mark'), ('Michelle');

INSERT INTO last_names (last_name) VALUES
                                       ('Smith'), ('Johnson'), ('Brown'), ('Davis'), ('Jones'),
                                       ('Garcia'), ('Miller'), ('Davis'), ('Rodriguez'), ('Martinez'),
                                       ('Anderson'), ('Taylor'), ('Wilson'), ('Thomas'), ('Moore');


INSERT INTO students (group_id, first_name, last_name)
SELECT
        1 + (random() * 9)::int AS group_id,
        first_names.first_name,
        last_names.last_name
FROM (
         SELECT first_name FROM first_names ORDER BY random() LIMIT 200
     ) first_names
         JOIN (
    SELECT last_name FROM last_names ORDER BY random() LIMIT 200
) last_names
              ON true;

INSERT INTO courses (course_name, course_description)
VALUES
    ('Math', 'Mathematics course'),
    ('Biology', 'Biology course'),
    ('Chemistry', 'Chemistry course'),
    ('Physics', 'Physics course'),
    ('History', 'History course'),
    ('Geography', 'Geography course'),
    ('English', 'English language course'),
    ('Spanish', 'Spanish language course'),
    ('Art', 'Art course'),
    ('Music', 'Music course');

INSERT INTO student_courses (student_id, course_id)
SELECT
    students.student_id,
    courses.course_id
FROM
    students
        CROSS JOIN courses
WHERE
        random() < 0.3
GROUP BY
    students.student_id,
    courses.course_id;
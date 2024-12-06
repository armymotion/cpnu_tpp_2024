-- Cleanup existing objects
DROP TABLE IF EXISTS academic_debts;

DROP TABLE IF EXISTS students;

DROP TABLE IF EXISTS subjects;

-- Create subjects table with serial id
CREATE TABLE subjects (
    id serial PRIMARY KEY,
    name text NOT NULL,
    credits integer NOT NULL,
    CONSTRAINT subjects_name_unique UNIQUE (name),
    CONSTRAINT credits_positive CHECK (credits > 0)
);

-- Create students table
CREATE TABLE students (
    id uuid NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL,
    group_name text NOT NULL,
    CONSTRAINT pk_students PRIMARY KEY (id),
    CONSTRAINT chk_name_not_empty CHECK (
        length(trim(first_name)) > 0
        AND length(trim(last_name)) > 0
    ),
    CONSTRAINT chk_group_name_format CHECK (
        group_name ~ '^[A-Z]{2,3}-\d{3}$'
    )
);

-- Create academic_debts table
CREATE TABLE academic_debts (
    id serial PRIMARY KEY,
    student_id uuid NOT NULL,
    subject_id integer NOT NULL,
    deadline_date date NOT NULL,
    CONSTRAINT fk_academic_debts_student FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_academic_debts_subject FOREIGN KEY (subject_id) REFERENCES subjects (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT uq_student_subject UNIQUE (student_id, subject_id)
);

-- Insert sample data
INSERT INTO
    subjects (name, credits)
VALUES ('Математичний аналіз', 5),
    ('Програмування', 6),
    ('Бази даних', 4),
    ('Операційні системи', 5),
    ('Мережі', 4),
    (
        'Алгоритми та структури даних',
        6
    );

INSERT INTO
    students (
        id,
        first_name,
        last_name,
        group_name
    )
VALUES (
        'c93f93e9-e27e-6ef1-1f92-01e6f97f3d0f',
        'Марія',
        'Марієнко',
        'ICS-220'
    ),
    (
        'b82f82d8-d16d-5de0-0f81-90d5f86e2c9f',
        'Степан',
        'Степаненко',
        'ICS-220'
    ),
    (
        'd04f04f0-f38f-7ff2-2f03-12f7f08f4e1f',
        'Олена',
        'Оленченко',
        'PI-220'
    ),
    (
        'a37a37a3-a61a-0aa5-5a36-45a0a31a7a4a',
        'Михайло',
        'Михайленко',
        'PI-220'
    );

INSERT INTO
    academic_debts (
        student_id,
        subject_id,
        deadline_date
    )
VALUES (
        'c93f93e9-e27e-6ef1-1f92-01e6f97f3d0f',
        1,
        '2024-08-25'
    ), -- Марія -> Мат. аналіз
    (
        'c93f93e9-e27e-6ef1-1f92-01e6f97f3d0f',
        2,
        '2024-08-20'
    ), -- Марія -> Програмування
    (
        'b82f82d8-d16d-5de0-0f81-90d5f86e2c9f',
        3,
        '2024-08-15'
    ), -- Степан -> Бази даних
    (
        'd04f04f0-f38f-7ff2-2f03-12f7f08f4e1f',
        4,
        '2024-08-28'
    ), -- Олена -> Операційні системи
    (
        'a37a37a3-a61a-0aa5-5a36-45a0a31a7a4a',
        5,
        '2024-08-22'
    );
-- Михайло -> Мережі
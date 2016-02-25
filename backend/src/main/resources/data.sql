
-- USER
INSERT INTO system_user(id, email, password, registration_date, deleted)
VALUES(nextval('seq_system_user_id'), 'max@schrack.info', 'Test1234', '2016-2-17 12:00:00.00', false);
INSERT INTO system_user(id, email, password, registration_date, deleted)
VALUES(nextval('seq_system_user_id'), 'niko@schrack.info', 'Asdf1234', '2016-2-17 12:00:00.00', false);

-- QUESTIONNAIRES FOR USER 1
INSERT INTO questionnaire(id, user_id, name, created, status, progressbar, deleted)
VALUES(nextval('seq_questionnaire_id'), 1, 'Test Questionnaire 1', '2016-2-17 12:00:00.00', 'draft', true, false);
INSERT INTO questionnaire(id, user_id, name, created, status, progressbar, deleted)
VALUES(nextval('seq_questionnaire_id'), 1, 'Test Questionnaire 2', '2016-2-16 13:00:00.00', 'open', true, false);
INSERT INTO questionnaire(id, user_id, name, created, status, progressbar, deleted)
VALUES(nextval('seq_questionnaire_id'), 2, 'Test Questionnaire 3', '2016-2-15 14:00:00.00', 'closed', true, false);

-- PAGES FOR QUESTIONNAIRE 1
INSERT INTO page(id, questionnaire_id, title, text, number, deleted)
VALUES(nextval('seq_page_id'), 1, 'Page 1 of Questionnaire 1', 'Test Text for Page 1 of Questionnaire 1', 0, false);
INSERT INTO page(id, questionnaire_id, title, text, number, deleted)
VALUES(nextval('seq_page_id'), 1, 'Page 2 of Questionnaire 1', 'Test Text for Page 2 of Questionnaire 1', 1, false);
INSERT INTO page(id, questionnaire_id, title, text, number, deleted)
VALUES(nextval('seq_page_id'), 1, 'Page 3 of Questionnaire 1', 'Test Text for Page 3 of Questionnaire 1', 2, false);

-- QUESTIONS FOR PAGE 1
  -- OPEN QUESTION 1
  INSERT INTO question(id, page_id, text, mandatory, position, question_type, deleted)
  VALUES(nextval('seq_question_id'), 1, 'OQ: Question 1 of Page 1', true, 0, 'OQ', false);
  INSERT INTO open_question(id, validation_type, is_long)
  VALUES(1, 'none', true);
  -- OPEN QUESTION 2
  INSERT INTO question(id, page_id, text, mandatory, position, question_type, deleted)
  VALUES(nextval('seq_question_id'), 1, 'OQ: Question 2 of Page 1', false, 0, 'OQ', false);
  INSERT INTO open_question(id, validation_type, is_long)
  VALUES(2, 'email', true);
  -- MULTIPLE CHOICE QUESTION 3
  INSERT INTO question(id, page_id, text, mandatory, position, question_type, deleted)
  VALUES(nextval('seq_question_id'), 1, 'MC: Question 3 of Page 1', false, 0, 'MC', false);
  INSERT INTO multiple_choice(id, is_single_choice)
  VALUES(3, false);
    -- ANSWER OF QUESTION 3
    INSERT INTO multiple_choice_answer(id, multiple_choice_id, text)
    VALUES(nextval('seq_multiple_choice_answer_id'), 3, 'A1 of MC 3');
    INSERT INTO multiple_choice_answer(id, multiple_choice_id, text)
    VALUES(nextval('seq_multiple_choice_answer_id'), 3, 'A2 of MC 3');
    INSERT INTO multiple_choice_answer(id, multiple_choice_id, text)
    VALUES(nextval('seq_multiple_choice_answer_id'), 3, 'A3 of MC 3');
    INSERT INTO multiple_choice_answer(id, multiple_choice_id, text)
    VALUES(nextval('seq_multiple_choice_answer_id'), 3, 'A4 of MC 3');


-- PARTICIPANTS
INSERT INTO participant(id, questionnaire_id, email, password, ip_address, creation_date, deleted)
VALUES(nextval('seq_participant_id'), 1, 'participant1@test.com', 'xxx', '127.0.0.1', '2016-2-14 12:00:00.00', false);
INSERT INTO participant(id, questionnaire_id, email, password, ip_address, creation_date, deleted)
VALUES(nextval('seq_participant_id'), 1, 'participant2@test.com', 'xxx', '128.0.0.1', '2016-2-24 18:00:00.00', false);
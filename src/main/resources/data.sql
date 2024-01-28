

insert into user_account (user_id, user_password, authority, email, nickname, memo ,created_at, updated_at)
values('ggamangso', '{noop}test1234', 'USER','test@mail.com', 'ggamang', null, now(), now());

insert into user_account (user_id, user_password, authority, email, nickname, memo ,created_at, updated_at)
values('ggamangso2', '{noop}test1234', 'USER','test2@mail.com', 'ggamang2', 'momo', now(), now());

insert into chat(chat_id, user_id, first_message,created_at, updated_at)
values(1, 'ggamangso','ltomaini0@tmall.com', now(),now());

insert into chat(chat_id, user_id, first_message,created_at, updated_at)
values(2, 'ggamangso','ltomaini0@tmall.com', now(),now());

insert into chat(chat_id, user_id, first_message,created_at, updated_at)
values(3, 'ggamangso','ltomaini0@tmall.com', now(),now());

insert into chat(chat_id, user_id, first_message,created_at, updated_at)
values(4, 'ggamangso','ltomaini0@tmall.com', now(),now());

insert into chat(chat_id, user_id, first_message,created_at, updated_at)
values(5, 'ggamangso2','ltomaini0@tmall.com', now(),now());

insert into chat(chat_id, user_id, first_message,created_at, updated_at)
values(6, 'ggamangso2','ltomaini0@tmall.com', now(),now());

insert into chat(chat_id, user_id, first_message,created_at, updated_at)
values(7, 'ggamangso2','ltomaini0@tmall.com', now(),now());

insert into chat(chat_id, user_id, first_message,created_at, updated_at)
values(8, 'ggamangso2','ltomaini0@tmall.com', now(),now());

insert into message (id, chat_id, role, content, corrected_content, is_bookmarked, message_memo, created_at, updated_at)
values (1, 1, 'USER', 'ltomaini0@tmall.com', null, true, 'Duis at velit eu est congue elementum.',now(), now()),
       (2, 1, 'ASSISTANT', 'lpharrow1@gnu.org', 'Male', true, 'In eleifend quam a odio.',now(), now()),
       (3, 1, 'USER', 'nburnyeat2@ustream.tv', 'Polygender', true, 'Nullam varius.',now(), now()),
       (4, 1, 'ASSISTANT', 'hscruton3@newyorker.com', null, true, 'Nunc purus.',now(), now()),
       (5, 2, 'USER', 'hwattingham4@yolasite.com', 'Male', false, 'Phasellus sit amet erat.',now(), now()),
       (6, 2, 'ASSISTANT', 'cflippini5@delicious.com', 'Female', false, 'Aliquam erat volutpat.',now(), now()),
       (7, 2, 'USER', 'rpottes6@globo.com', null, false, null,now(), now()),
       (8, 2, 'ASSISTANT', 'gdelayglesia7@webnode.com', null, true, 'Phasellus in felis.',now(), now()),
       (9, 3 , 'USER', 'jwyeth8@trellian.com', null, false, 'Vestibulum rutrum rutrum neque.',now(), now()),
       (10, 3, 'ASSISTANT', 'pkeelinge9@mashable.com', null, true, 'Morbi vel lectus in quam fringilla rhoncus.',now(), now()),
       (11, 3, 'USER', 'ewarena@wiley.com', 'Male', true, 'Phasellus in felis.',now(), now()),
       (12, 3, 'ASSISTANT', 'mantoniazzib@hibu.com', 'Male', true, null,now(), now()),
       (13, 4, 'USER', 'cstowc@over-blog.com', null, true, 'Mauris sit amet eros.',now(), now()),
       (14, 4, 'ASSISTANT', 'asheasbyd@sakura.ne.jp', null, false, 'Nam tristique tortor eu pede.',now(), now()),
       (15, 4, 'USER', 'cjahne@illinois.edu', 'Female', false, 'Suspendisse ornare consequat lectus.',now(), now()),
       (16, 4, 'ASSISTANT', 'fdymentf@lulu.com', 'Male', true, 'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla.',now(), now()),
       (17, 5, 'USER', 'dcrannageg@mac.com', 'Female', false, 'Praesent blandit.',now(), now()),
       (18, 5, 'ASSISTANT', 'ksimonsenh@qq.com', 'Male', false, 'Donec posuere metus vitae ipsum.',now(), now()),
       (19, 5, 'USER', 'hpellingtoni@home.pl', 'Polygender', false, 'Maecenas tincidunt lacus at velit.',now(), now()),
       (20, 5, 'ASSISTANT', 'lecobj@cargocollective.com', 'Female', true, 'Pellentesque eget nunc.',now(), now()),
       (21, 6, 'USER', 'cgethenk@imageshack.us', 'Male', true, null,now(), now()),
       (22, 6, 'ASSISTANT', 'hjillsl@acquirethisname.com', 'Male', false,'Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl.',now(), now()),
       (23, 6, 'USER', 'ditzhayekm@aol.com', null, true,'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.',now(), now()),
       (24, 6, 'ASSISTANT', 'zbullinghamn@cnbc.com', 'Male', true, 'Praesent blandit.',now(), now()),
       (25, 7, 'USER', 'kiveo@omniture.com', 'Female', true, 'Phasellus sit amet erat.',now(), now()),
       (26, 7, 'ASSISTANT', 'dshillingfordp@loc.gov', 'Male', false, null,now(), now()),
       (27, 7, 'USER', 'iskedgellq@princeton.edu', null, true, 'Aenean auctor gravida sem.',now(), now()),
       (28, 7, 'ASSISTANT', 'abraddenr@who.int', null, true, 'Curabitur in libero ut massa volutpat convallis.',now(), now()),
       (29, 8, 'USER', 'bbooleys@narod.ru', null, true,'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam.',now(), now()),
       (30, 8, 'ASSISTANT', 'cgalletleyt@yolasite.com', 'Non-binary', false, 'In sagittis dui vel nisl.',now(), now()),
       (31, 8, 'USER', 'cbursnellu@rediff.com', 'Female', true, 'Suspendisse accumsan tortor quis turpis.',now(), now()),
       (32, 8, 'ASSISTANT', 'jcarthyv@bigcartel.com', 'Male', false, 'Duis aliquam convallis nunc.',now(), now()),
       (33, 1, 'USER', 'mcogglesw@geocities.com', null, true, 'Vivamus in felis eu sapien cursus vestibulum.',now(), now()),
       (34, 1, 'ASSISTANT', 'temsleyx@xinhuanet.com', 'Male', false, null,now(), now()),
       (35, 1, 'USER', 'rmacdougaly@washington.edu', null, true, null,now(), now()),
       (36, 1, 'ASSISTANT', 'arodenburgz@go.com', 'Female', true, null,now(), now()),
       (37, 1, 'USER', 'idarnbrook10@ow.ly', 'Female', true, 'Morbi vel lectus in quam fringilla rhoncus.',now(), now()),
       (38, 1, 'ASSISTANT', 'gloughney11@utexas.edu', 'Female', true, 'Proin at turpis a pede posuere nonummy.',now(), now()),
       (39, 1, 'USER', 'jeathorne12@dropbox.com', 'Male', false, 'Nulla tellus.',now(), now()),
       (40, 1, 'ASSISTANT', 'fasplin13@blogtalkradio.com', 'Male', true, null,now(), now()),
       (41, 1, 'USER', 'kvenny14@cocolog-nifty.com', 'Female', true, 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.',now(), now()),
       (42, 1, 'ASSISTANT', 'olamond15@dion.ne.jp', null, false, null,now(), now()),
       (43, 1, 'USER', 'fdubois16@nature.com', 'Male', false, 'Nam dui.',now(), now()),
       (44, 1, 'ASSISTANT', 'tglyne17@amazon.co.jp', 'Male', true, null,now(), now()),
       (45, 1, 'USER', 'kbunning18@earthlink.net', 'Female', false, 'In eleifend quam a odio.',now(), now()),
       (46, 1, 'ASSISTANT', 'mmariel19@slashdot.org', 'Male', false, 'Maecenas pulvinar lobortis est.',now(), now()),
       (47, 1, 'USER', 'nschuster1a@imageshack.us', 'Male', false, null,now(), now()),
       (48, 1, 'ASSISTANT', 'creavell1b@xrea.com', null, true,  'Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.',now(), now()),
       (49, 1, 'USER', 'nbotcherby1c@twitter.com', 'Male', true, 'Praesent id massa id nisl venenatis lacinia.',now(), now()),
       (50, 1, 'ASSISTANT', 'rmacnaughton1d@msn.com', null, false, 'Nam dui.',now(), now()),
       (51, 1, 'USER', 'bheams1e@stumbleupon.com', 'Genderfluid', true, 'Mauris ullamcorper purus sit amet nulla.',now(), now()),
       (52, 1, 'ASSISTANT', 'avampouille1f@marketwatch.com', null, false, 'Duis bibendum.',now(), now()),
       (53, 1, 'USER', 'mharbron1g@howstuffworks.com', 'Male', true, 'Pellentesque viverra pede ac diam.',now(), now()),
       (54, 1, 'ASSISTANT', 'mmcpharlain1h@ycombinator.com', null, true, null,now(), now()),
       (55, 1, 'USER', 'msheather1i@house.gov', 'Male', true, 'Proin risus.',now(), now()),
       (56, 1, 'ASSISTANT', 'mmacghee1j@goo.ne.jp', 'Male', true, 'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo.',now(), now()),
       (57, 1, 'USER', 'ltort1k@canalblog.com', null, true, null,now(), now()),
       (58, 1, 'ASSISTANT', 'ncastanos1l@loc.gov', 'Male', true, 'Praesent id massa id nisl venenatis lacinia.',now(), now()),
       (59, 1, 'USER', 'averdie1m@w3.org', 'Male', false, 'Cras pellentesque volutpat dui.',now(), now()),
       (60, 1, 'ASSISTANT', 'dreeson1n@nyu.edu', 'Female', true, 'Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl.',now(), now()),
       (61, 1, 'USER', 'asowden1o@fc2.com', 'Female', true, 'Aliquam erat volutpat.',now(), now()),
       (62, 1, 'ASSISTANT', 'asorrell1p@google.es', 'Female', false, 'Suspendisse ornare consequat lectus.',now(), now()),
       (63, 1, 'USER', 'eboyland1q@cargocollective.com', 'Non-binary', false, null,now(), now()),
       (64, 1, 'ASSISTANT', 'ohegge1r@china.com.cn', 'Female', true, 'In hac habitasse platea dictumst.',now(), now()),
       (65, 1, 'USER', 'mprobbings1s@furl.net', 'Female', false, 'Morbi porttitor lorem id ligula.',now(), now()),
       (66, 1, 'ASSISTANT', 'egalletly1t@creativecommons.org', 'Male', false,'Duis consequat dui nec nisi volutpat eleifend.',now(), now()),
       (67, 1, 'USER', 'mbraferton1u@lulu.com', null, true, 'Proin eu mi.',now(), now()),
       (68, 1, 'ASSISTANT', 'gtilburn1v@tripod.com', 'Female', true, 'Suspendisse potenti.',now(), now()),
       (69, 1, 'USER', 'apund1w@umn.edu', null, false, 'Nam dui.',now(), now()),
       (70, 1, 'ASSISTANT', 'obesset1x@netvibes.com', null, true, 'Vivamus tortor.',now(), now()),
       (71, 1, 'USER', 'fboak1y@irs.gov', 'Male', false, null,now(), now()),
       (72, 1, 'ASSISTANT', 'ashone1z@npr.org', 'Female', true, 'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci.',now(), now()),
       (73, 1, 'USER', 'dpennock20@mit.edu', 'Male', true, 'Duis consequat dui nec nisi volutpat eleifend.',now(), now()),
       (74, 1, 'ASSISTANT', 'njagiello21@discuz.net', 'Male', true, 'Donec quis orci eget orci vehicula condimentum.',now(), now()),
       (75, 1, 'USER', 'scahalan22@fema.gov', 'Male', false, null,now(), now()),
       (76, 1, 'ASSISTANT', 'fcrampin23@wordpress.com', null, true, 'Morbi porttitor lorem id ligula.',now(), now()),
       (77, 1, 'USER', 'dkember24@buzzfeed.com', 'Genderfluid', false, 'Sed sagittis.',now(), now()),
       (78, 1, 'ASSISTANT', 'dgiffard25@xrea.com', 'Female', true, 'Praesent lectus.',now(), now()),
       (79, 1, 'USER', 'flangan26@zimbio.com', 'Genderqueer', false, null,now(), now()),
       (80, 1, 'ASSISTANT', 'smacskeaghan27@etsy.com', null, true, 'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.',now(), now()),
       (81, 1, 'USER', 'pdyte28@furl.net', 'Male', true, 'Mauris sit amet eros.',now(), now()),
       (82, 1, 'ASSISTANT', 'gdinehart29@scribd.com', null, false, 'Aliquam sit amet diam in magna bibendum imperdiet.',now(), now()),
       (83, 1, 'USER', 'bswatradge2a@paginegialle.it', 'Female', false, 'Praesent id massa id nisl venenatis lacinia.',now(), now()),
       (84, 1, 'ASSISTANT', 'mfolke2b@odnoklassniki.ru', 'Male', false, 'Ut tellus.',now(), now()),
       (85, 1, 'USER', 'sboyen2c@artisteer.com', 'Male', true, 'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.',now(), now()),
       (86, 1, 'ASSISTANT', 'ztarte2d@ox.ac.uk', null, true, 'Pellentesque ultrices mattis odio.',now(), now()),
       (87, 1, 'USER', 'smewton2e@independent.co.uk', null, true, 'Pellentesque at nulla.',now(), now()),
       (88, 1, 'ASSISTANT', 'alearman2f@cdbaby.com', null, true, 'Integer tincidunt ante vel ipsum.',now(), now()),
       (89, 1, 'USER', 'wmullen2g@rambler.ru', 'Male', true, 'Integer ac neque.',now(), now()),
       (90, 1, 'ASSISTANT', 'nannable2h@xing.com', null, false,'In est risus, auctor sed, tristique in, tempus sit amet, sem.',now(), now()),
       (91, 1, 'USER', 'sbarfield2i@is.gd', 'Male', true, 'In hac habitasse platea dictumst.',now(), now()),
       (92, 1, 'ASSISTANT', 'icouthard2j@ehow.com', null, true, 'Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl.',now(), now()),
       (93, 1, 'USER', 'trowlson2k@mac.com', 'Female', true, null,now(), now()),
       (94, 1, 'ASSISTANT', 'moldnall2l@163.com', null, false, 'Donec quis orci eget orci vehicula condimentum.',now(), now()),
       (95, 1, 'USER', 'fmustchin2m@sakura.ne.jp', 'Female', false, 'Sed sagittis.',now(), now()),
       (96, 1, 'ASSISTANT', 'wocorren2n@sciencedaily.com', 'Female', false, null,now(), now()),
       (97, 1, 'USER', 'cfreda2o@paginegialle.it', 'Female', true, null,now(), now()),
       (98, 1, 'ASSISTANT', 'lvannacci2p@ucoz.ru', 'Female', false, 'Nulla tempus.',now(), now()),
       (99, 1, 'USER', 'civantyev2q@last.fm', 'Male', true, 'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla.',now(), now()),
       (100, 1, 'ASSISTANT', 'odyson2r@tuttocitta.it', 'Male', false, 'Integer ac neque.',now(), now());

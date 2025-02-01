package org.zerock.mallapi.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.dto.TodoDTO;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("서비스 테스트")
                .writer("tester")
                .dueDate(LocalDate.of(2025,02,01))
                .build();

        Long tno = todoService.register(todoDTO);
        log.info("TNO: " + tno);
    }

    @Test
    public void testGet() {
        Long tno = 101L;
        TodoDTO todoDTO = todoService.get(tno);

        log.info(todoDTO);
    }

    @Test
    public void testModify() {
        Long tno = 101L;

        TodoDTO todoDTO = TodoDTO.builder()
                .tno(tno)
                .title("수정된 제목")
                .writer("tester")
                .dueDate(LocalDate.of(2025, 2, 1))
                .complete(true)
                .build();

        todoService.modify(todoDTO);

        log.info("수정 완료: " + todoDTO);

    }

    @Test
    public void testRemove() {
        Long tno = 101L; // 삭제할 Todo ID

        todoService.remove(tno);

        log.info("삭제 완료: tno = " + tno);
    }
}
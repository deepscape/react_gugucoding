package org.zerock.mallapi.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.mallapi.domain.Todo;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void test1() {
        log.info("-------------------------");
        log.info(todoRepository);
    }

    @Test
    public void testInsert() {
        IntStream.range(0, 100)
                .mapToObj(i -> Todo.builder()
                                    .title("Title..." + i)
                                    .dueDate(LocalDate.of(2025, 2, 1))
                                    .writer("user00")
                                    .build())
                .forEach(todoRepository::save);
    }

    @Test
    public void testRead() {
        //존재하는 번호로 확인
        Long tno = 31L;

        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();

        log.info(todo);
    }

    @Test
    public void testModify() {
        Long tno = 33L;

        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        todo.changeTitle("Modified 33...");
        todo.changeComplete(true);
        todo.changeDueDate(LocalDate.of(2025,02,01));

        todoRepository.save(todo);
    }

    @Test
    public void testDelete() {
        Long tno = 1L;
        todoRepository.deleteById(tno);
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result.getTotalElements());
        result.getContent().stream().forEach(todo -> log.info(todo));
    }
}

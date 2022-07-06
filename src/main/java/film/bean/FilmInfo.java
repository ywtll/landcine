package film.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;

@Data
@AllArgsConstructor
public class FilmInfo {
    private String title;
    private String author;
    private ImageIcon image;
    private String introduction;
    private int view;
    private int hot;
}

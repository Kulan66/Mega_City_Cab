package com.example.mega_city_cab.service;

import com.example.mega_city_cab.dao.HelpDAO;
import com.example.mega_city_cab.model.Help;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HelpServiceTest {

    private HelpService helpService;
    private HelpDAO helpDAO;
    private Help help;

    @BeforeEach
    void setUp() {
        helpDAO = new HelpDAO() {
            private List<Help> guidelines = new ArrayList<>();

            @Override
            public void addGuideline(Help help) throws SQLException {
                guidelines.add(help);
            }

            @Override
            public void updateGuideline(Help help) throws SQLException {
                int index = guidelines.indexOf(getHelp(help.getHelpID()));
                if (index >= 0) {
                    guidelines.set(index, help);
                }
            }

            @Override
            public void deleteGuideline(int helpID) throws SQLException {
                guidelines.removeIf(h -> h.getHelpID() == helpID);
            }

            @Override
            public List<Help> getAllGuidelines() throws SQLException {
                return new ArrayList<>(guidelines);
            }

            @Override
            public Help getHelp(int helpID) throws SQLException {
                return guidelines.stream()
                        .filter(h -> h.getHelpID() == helpID)
                        .findFirst()
                        .orElse(null);
            }
        };
        helpService = new HelpService();
        helpService.setHelpDAO(helpDAO);

        help = new Help();
        help.setHelpID(1);
        help.setGuideline("Test Guideline");
    }

    @Test
    void addGuideline() throws SQLException {
        helpService.addGuideline(help);
        Help retrievedHelp = helpService.getHelp(1);
        assertNotNull(retrievedHelp);
        assertEquals(help.getHelpID(), retrievedHelp.getHelpID());
    }

    @Test
    void updateGuideline() throws SQLException {
        helpService.addGuideline(help);
        help.setGuideline("Updated Guideline");
        helpService.updateGuideline(help);
        Help retrievedHelp = helpService.getHelp(1);
        assertNotNull(retrievedHelp);
        assertEquals("Updated Guideline", retrievedHelp.getGuideline());
    }

    @Test
    void deleteGuideline() throws SQLException {
        helpService.addGuideline(help);
        helpService.deleteGuideline(1);
        Help retrievedHelp = helpService.getHelp(1);
        assertNull(retrievedHelp);
    }

    @Test
    void getAllGuidelines() throws SQLException {
        helpService.addGuideline(help);
        List<Help> retrievedGuidelines = helpService.getAllGuidelines();
        assertNotNull(retrievedGuidelines);
        assertEquals(1, retrievedGuidelines.size());
    }

    @Test
    void getHelp() throws SQLException {
        helpService.addGuideline(help);
        Help retrievedHelp = helpService.getHelp(1);
        assertNotNull(retrievedHelp);
        assertEquals(help.getHelpID(), retrievedHelp.getHelpID());
    }
}
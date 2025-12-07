package org.skooma;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.inject.Inject;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.input.MouseAdapter;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.*;

@Slf4j
class QuantifyPanel extends PluginPanel
{
    private IconTextField searchBar;

    //net.runelite.client.ui.components.
    private ProgressBar progBar;


    void init(Client client, QuantifyConfig config)
    {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        setBackground(ColorScheme.DARK_GRAY_COLOR);
        setLayout(new GridBagLayout());

        // Expand sub items to fit width of panel, align to top of panel
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0;
        c.insets = new Insets(0, 0, 10, 0);

        searchBar = new IconTextField();
        searchBar.setIcon(IconTextField.Icon.SEARCH);
        searchBar.setPreferredSize(new Dimension(PluginPanel.PANEL_WIDTH - 20, 30));
        searchBar.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        searchBar.setHoverBackgroundColor(ColorScheme.DARK_GRAY_HOVER_COLOR);
        searchBar.setMinimumSize(new Dimension(0, 30));
        searchBar.addActionListener(e -> lookup());

        add(searchBar);
        c.gridy++;

//        progBar = new ProgressBar();
//        progBar.setLeftLabel("FOOO");
//        progBar.setMinimumSize(new Dimension(PluginPanel.PANEL_WIDTH - 20, 30));
//        progBar.setToolTipText("fuck");
//        progBar.setPreferredSize(new Dimension(PluginPanel.PANEL_WIDTH - 20, 30));
//        progBar.add(searchBar, 1);
//        add(progBar);
//
//        c.gridy++;
    }

    ///  Tries to find itemID that corresponds with input
    private void lookup()
    {
        System.out.println(searchBar.getText());
    }
}

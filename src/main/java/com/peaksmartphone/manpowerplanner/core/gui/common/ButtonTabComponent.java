package com.peaksmartphone.manpowerplanner.core.gui.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * <p> Title: </p>
 * 
 * <b>Description:</b> 
 * <p> Insert Description here </p>
 *
 * @author mko
 *
 *  <p>Company: Peak Solution GmbH</p>
 *
 * $Rev: $:     Revision of last commit<br/>
 * $Author: $:  Author of last commit<br/>
 * $Date: $:    Date of last commit
 * 
 */
public class ButtonTabComponent extends JPanel 
{
  private static final long serialVersionUID = 2069007020555454866L;

  private static final int MAX_VISIBLE_CHAR_COUNT = 20;

  private final ClosableTabbedPane mPane;
  private final ClosableTabPaneAction mListener; 

  public ButtonTabComponent(final ClosableTabbedPane pane, ClosableTabPaneAction pListener) 
  {
    super(new GridBagLayout());
    
    mListener = pListener;

    this.mPane = pane;
    setOpaque(false);

    //make JLabel read titles from JTabbedPane
    final JLabel label = new JLabel() 
    {
      private static final long serialVersionUID = -8772855109148263940L;

      public String getText() 
      {
        int i = pane.indexOfTabComponent(ButtonTabComponent.this);
        
        if (i != -1) 
        {
          String str = pane.getTitleAt(i);

          if (str.length() > MAX_VISIBLE_CHAR_COUNT)
          {
            str = str.substring(0, MAX_VISIBLE_CHAR_COUNT);
            str += "...";
          }
          return str;
        }
        return null;
      }
    };

    //add more space between the label and the button
    label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
    add(label, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
    
    
    //tab button
    JButton button = new TabButton();
    add(button, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
  }

  private class TabButton extends JButton implements ActionListener 
  {
    public TabButton() 
    {
      int size = 17;
      setPreferredSize(new Dimension(size, size));
      setToolTipText("close this tab");
      
      //Make the button looks the same for all Laf's
      setUI(new BasicButtonUI());
      
      //Make it transparent
      setContentAreaFilled(false);
      
      //No need to be focusable
      setFocusable(false);
      setBorder(BorderFactory.createEtchedBorder());
      setBorderPainted(false);
      
      //Making nice rollover effect
      //we use the same listener for all buttons
      addMouseListener(buttonMouseListener);
      setRolloverEnabled(true);
      
      //Close the proper tab by clicking the button
      addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) 
    {
      int i = mPane.indexOfTabComponent(ButtonTabComponent.this);

      mListener.onCloseTab(mPane, i);
    }

    public void updateUI() 
    {
    }

    //paint the cross
    protected void paintComponent(Graphics g) 
    {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g.create();
      //shift the image for pressed buttons
      if (getModel().isPressed()) 
      {
        g2.translate(1, 1);
      }
      g2.setStroke(new BasicStroke(2));
      g2.setColor(Color.BLACK);
      if (getModel().isRollover()) 
      {
        g2.setColor(Color.DARK_GRAY);
      }
      int delta = 6;
      g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
      g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
      g2.dispose();
    }
  }

  private final static MouseListener buttonMouseListener = new MouseAdapter() 
  {
    public void mouseEntered(MouseEvent e) 
    {
      Component component = e.getComponent();
      if (component instanceof AbstractButton) 
      {
        AbstractButton button = (AbstractButton) component;
        button.setBorderPainted(true);
      }
    }

    public void mouseExited(MouseEvent e) 
    {
      Component component = e.getComponent();
      if (component instanceof AbstractButton) 
      {
        AbstractButton button = (AbstractButton) component;
        button.setBorderPainted(false);
      }
    }
  };
}


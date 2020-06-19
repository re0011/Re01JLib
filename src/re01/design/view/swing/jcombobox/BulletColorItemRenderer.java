/*
 * Copyright (C) 2020 LE CLERRE Renaud
 *
 * This file is part of Re01JLib.
 *
 * Re01JLib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Re01JLib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Re01JLib. If not, see <http://www.gnu.org/licenses/>.
 */

package re01.design.view.swing.jcombobox;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import re01.design.view.swing.GridBagLayout;
import re01.design.view.swing.JLabel;
import re01.design.view.swing.JPanel;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class BulletColorItemRenderer extends JPanel implements ListCellRenderer {
	
	private JComboBoxTypeEnum comboboxType;
	private JLabel labelItem;
	
	public BulletColorItemRenderer( JComboBoxTypeEnum comboboxType ) {
		super();
		this.comboboxType = comboboxType;
		
		setLayout( new GridBagLayout() );
		
		labelItem = new JLabel( "" );
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.insets = new Insets(
			re01.environment.Parameters.get_RECOMMENDED_COMPONENT_MARGIN_HEIGHT(), 
			re01.environment.Parameters.get_RECOMMENDED_COMPONENT_MARGIN_WIDTH(), 
			re01.environment.Parameters.get_RECOMMENDED_COMPONENT_MARGIN_HEIGHT(), 
			re01.environment.Parameters.get_RECOMMENDED_COMPONENT_MARGIN_WIDTH()
		);

		labelItem.setOpaque( true );
		labelItem.setHorizontalAlignment( JLabel.LEFT );
		labelItem.setPreferredSize( new Dimension(Parameters.getIconsSize(), Parameters.getIconsSize()) );

		add(labelItem, constraints);
		setBackground( Parameters.getThemeSelected().getBackgroundColor().getRgbColor() );
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		
		switch ( comboboxType ) {
			case Colors:
			case ColorsOrImagesIcons:
			case ColorsWithLabel:
				
				re01.design.image.Images icons = new re01.design.image.Images();
				java.awt.Color color = null;
				ImageIcon imageIcon = null;
				
				switch ( comboboxType ) {
					case Colors:
						if ( value != null ) {
							color = new java.awt.Color((Integer)value);
							labelItem.setIcon( icons.createImageIcon( isSelected, color ) );
						} else
							labelItem.setIcon( null );
						break;
					case ColorsOrImagesIcons:
						if ( value != null ) {
							try {
								color = new java.awt.Color((Integer)value);
								labelItem.setIcon( icons.createImageIcon( isSelected, color ) );
							} catch ( Exception e ) { }
							try {
								imageIcon = (ImageIcon) value;
								labelItem.setIcon( imageIcon );
							} catch ( Exception e ) { }
						} else
							labelItem.setIcon( null );
						break;
					case ColorsWithLabel:
						Object[] item = (Object[]) value;
						labelItem.setText((String)item[0]);
						if (item[1] != null) {
							color = new java.awt.Color((Integer)item[1]);
							labelItem.setIcon( icons.createImageIcon( isSelected, color ) );
						} else
							labelItem.setIcon( null );
						break;
				}

				if (isSelected) {
					labelItem.setForeground( Parameters.getThemeSelected().getForegroundColor().getRgbColor() );
					labelItem.setBackground( Parameters.getThemeSelected().getBackgroundColor().getRgbColor() );
				} else {
					labelItem.setForeground( Parameters.getThemeSelected().getForegroundColor().getRgbColor() );
					labelItem.setBackground( Parameters.getThemeSelected().getBackgroundColor().getRgbColor() );
				}
				break;
		}
        return this;
	}
	
}

/*
 * Copyright (C) 2020-2021 LE CLERRE Renaud
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

package re01.design.image;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import re01.design.color.Color;
import re01.design.color.ColorAttributeTypeEnum;
import re01.design.color.ColorTypeEnum;
import re01.exception.Re01JLibException;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class Images {
	
	protected final ImageIcon GLOBAL_IMAGE_ICON_FOLDER = createGlobalImageIconFolder( Parameters.getIconsSize() );
	protected final ImageIcon GLOBAL_IMAGE_ICON_FILE = createGlobalImageIconFile( Parameters.getIconsSize() );
	protected final ImageIcon GLOBAL_IMAGE_ICON_INFO = createGlobalImageIconInfo( Parameters.getIconsSize() );
	protected final ImageIcon GLOBAL_IMAGE_ICON_OK = createGlobalImageIconOk( Parameters.getIconsSize() );
	protected final ImageIcon GLOBAL_IMAGE_ICON_ERROR = createGlobalImageIconError( Parameters.getIconsSize() );
	protected final ImageIcon GLOBAL_IMAGE_ICON_CAUTION = createGlobalImageIconCaution( Parameters.getIconsSize() );
	protected final ImageIcon GLOBAL_IMAGE_ICON_NOT_SAVED = createGlobalImageIconNotSaved( Parameters.getIconsSize() );
	protected final ImageIcon GLOBAL_IMAGE_ICON_SEARCH = createGlobalImageIconSearch(Parameters.getIconsSize() );

	public ImageIcon get_GLOBAL_IMAGE_ICON_FOLDER() {
		return GLOBAL_IMAGE_ICON_FOLDER;
	}

	public ImageIcon get_GLOBAL_IMAGE_ICON_FILE() {
		return GLOBAL_IMAGE_ICON_FILE;
	}

	public ImageIcon get_GLOBAL_IMAGE_ICON_INFO() {
		return GLOBAL_IMAGE_ICON_INFO;
	}

	public ImageIcon get_GLOBAL_IMAGE_ICON_OK() {
		return GLOBAL_IMAGE_ICON_OK;
	}

	public ImageIcon get_GLOBAL_IMAGE_ICON_ERROR() {
		return GLOBAL_IMAGE_ICON_ERROR;
	}

	public ImageIcon get_GLOBAL_IMAGE_ICON_CAUTION() {
		return GLOBAL_IMAGE_ICON_CAUTION;
	}

	public ImageIcon get_GLOBAL_IMAGE_ICON_NOT_SAVED() {
		return GLOBAL_IMAGE_ICON_NOT_SAVED;
	}

	public ImageIcon get_GLOBAL_IMAGE_ICON_SEARCH() {
		return GLOBAL_IMAGE_ICON_SEARCH;
	}
	
	public ImageIcon getImage( ImageTypeEnum imageType ) {
		ImageIcon imageIcon = null;
		switch ( imageType ) {
			case GlobalImageIconInfo:
				imageIcon = GLOBAL_IMAGE_ICON_INFO;
				break;
			case GlobalImageIconOk:
				imageIcon = GLOBAL_IMAGE_ICON_OK;
				break;
			case GlobalImageIconError:
				imageIcon = GLOBAL_IMAGE_ICON_ERROR;
				break;
			case GlobalImageIconCaution:
				imageIcon = GLOBAL_IMAGE_ICON_CAUTION;
				break;
			case GlobalImageIconFolder:
				imageIcon = GLOBAL_IMAGE_ICON_FOLDER;
				break;
			case GlobalImageIconFile:
				imageIcon = GLOBAL_IMAGE_ICON_FILE;
				break;
			case GlobalImageIconSearch:
				imageIcon = GLOBAL_IMAGE_ICON_SEARCH;
				break;
			case GlobalImageIconNotSaved:
				imageIcon = GLOBAL_IMAGE_ICON_NOT_SAVED;
				break;
		}
		return imageIcon;
	}
	
	public ImageIcon getImage( String imageTypeStr ) {
		ImageIcon imageIcon = null;
		String iconTypeStrLower = imageTypeStr.toLowerCase();
		ImageTypeEnum[] iconsTypes = ImageTypeEnum.values();
		ImageTypeEnum iconTypeFound = null;
		for ( int i = 0; i < iconsTypes.length; i++ ) {
			ImageTypeEnum iconType = iconsTypes[i];
			if ( iconType.toString().toLowerCase().equals(iconTypeStrLower) == true ) {
				iconTypeFound = iconType;
				break;
			}
		}
		if ( iconTypeFound != null )
			imageIcon = Images.this.getImage( iconTypeFound );
		return imageIcon;
	}
	
	public ImageIcon createImageIcon( URL imageUrl, String description ) throws Re01JLibException {
		ImageIcon imageIcon = null;
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read( imageUrl );
		} catch (IOException ex) {
			throw new Re01JLibException(ex);
		}
		if ( bufferedImage != null ) {
			Graphics2D g2d = bufferedImage.createGraphics();
			g2d.drawImage( bufferedImage, 0, 0, null );

			imageIcon = new ImageIcon( bufferedImage );
			g2d.dispose();
		}
		
		if ( description != null )
			imageIcon.setDescription( description );
		
		return imageIcon;
	}
	
	public ImageIcon createImageIcon( boolean isSelected, java.awt.Color color ) {
		ImageIcon imageIcon;
		BufferedImage bufferedImage = new BufferedImage(Parameters.getIconsSize(), Parameters.getIconsSize(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (isSelected)
			g2d.setColor( Parameters.getThemeSelected().getBackgroundColor().getRgbColor() );
		else
			g2d.setColor( Parameters.getThemeSelected().getBackgroundColor().getRgbColor() );
		for (int f = 0; f < Parameters.getIconsSize(); f++)
			g2d.drawLine(0, f, Parameters.getIconsSize(), f);
		g2d.setPaint(color);
		g2d.fillRoundRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), Parameters.getIconsSize(), Parameters.getIconsSize());
		imageIcon = new ImageIcon( bufferedImage );
		g2d.dispose();
		return imageIcon;
	}
	
	public ImageIcon mergeImagesIcon( ImageIcon imageIcon1, ImageIcon imageIcon2 ) {
		ImageIcon imageIcon;
		
		BufferedImage bufferedImage1 = (BufferedImage) imageIcon1.getImage();
		BufferedImage bufferedImage2 = (BufferedImage) imageIcon2.getImage();
		
		int imageIcon1Width = imageIcon1.getIconWidth();
		int imageIcon1DoubleWidth = imageIcon1Width * 2;
		
		BufferedImage combinedBufferedImage = new BufferedImage(imageIcon1DoubleWidth, imageIcon1.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = combinedBufferedImage.createGraphics();
		g2d.drawImage( bufferedImage1, 0, 0, null );
		g2d.drawImage( bufferedImage2, imageIcon1Width, 0, null );
		
		imageIcon = new ImageIcon( combinedBufferedImage );
		g2d.dispose();
		return imageIcon;
	}
	
	public ImageIcon mergeImagesIcon( URL imageUrl, ImageIcon imageIcon2 ) throws Re01JLibException {
		ImageIcon imageIcon = null;
		BufferedImage bufferedImage1 = null;
		try {
			bufferedImage1 = ImageIO.read( imageUrl );
		} catch (IOException ex) {
			throw new Re01JLibException(ex);
		}
		if ( bufferedImage1 != null ) {
			BufferedImage bufferedImage2 = (BufferedImage) imageIcon2.getImage();

			BufferedImage combinedBufferedImage = new BufferedImage(Parameters.getIconsSize() * 2, Parameters.getIconsSize(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = combinedBufferedImage.createGraphics();
			g2d.drawImage( bufferedImage1, 0, 0, null );
			g2d.drawImage( bufferedImage2, Parameters.getIconsSize(), 0, null );

			imageIcon = new ImageIcon( combinedBufferedImage );
			g2d.dispose();
		}
		return imageIcon;
	}
	
	public ImageIcon createGlobalImageIconInfo( int size ) {
		ImageIcon imageIcon = null;
		
		BufferedImage bufferedImage = new BufferedImage( size, size, BufferedImage.TYPE_INT_ARGB );
		Graphics2D g2d = bufferedImage.createGraphics();
		
		g2d.setColor(new Color( ColorTypeEnum.BlueYonder, ColorAttributeTypeEnum.Background ).getRgbColor() );
		
		Float width = new Float(size) / new Float(3);
		int widthInt = width.intValue();
		if ( widthInt < 2 )
			widthInt = 2;
		
		Float sizeHalf = new Float(size) / new Float(2);
		int sizeHalfInt = sizeHalf.intValue();
		
		Float sizePart8 = new Float(size) / new Float(8);
		int sizePart8Int = sizePart8.intValue();
		if ( sizePart8Int < 1 )
			sizePart8Int = 1;
		Float limit = sizePart8 * new Float(3);
		int limitInt = limit.intValue();
		
		for ( int i = 0; i < size; i++ ) {
			if ( i < sizeHalfInt ) {
				if ( i > sizePart8Int && i < limitInt ) {
					g2d.fillRect(widthInt, i, widthInt, 1);
				}
			} else if ( i > sizeHalfInt ) {
				g2d.fillRect(widthInt, i, widthInt, 1);
			}
		}
		
		imageIcon = new ImageIcon( bufferedImage );
		g2d.dispose();
		
		imageIcon.setDescription( ImageTypeEnum.GlobalImageIconInfo.toString() );
		
		return imageIcon;
	}
	
	public ImageIcon createGlobalImageIconOk( int size ) {
		ImageIcon imageIcon = null;
		
		BufferedImage bufferedImage = new BufferedImage( size, size, BufferedImage.TYPE_INT_ARGB );
		Graphics2D g2d = bufferedImage.createGraphics();
		
		Float borderSize = new Float(size) / new Float(10);
		int borderSizeInt = borderSize.intValue();
		if ( borderSizeInt < 2 )
			borderSizeInt = 2;
		
		Float sizePart4 = new Float(size) / new Float(4);
		int sizePart4Int = sizePart4.intValue();
		
		Float sizePart8 = new Float(size) / new Float(8);
		int sizePart8int = sizePart8.intValue();
		if ( sizePart8int < 1 )
			sizePart8int = 1;
		int sizeLessSizePart8int = size - sizePart8int;
		
		Float sizePart12 = new Float(size) / new Float(12);
		int sizePart12Int = sizePart12.intValue();
		if ( sizePart12Int < 1 )
			sizePart12Int = 1;
		
		Integer x1 = null;
		
		for ( int i = 0; i < size; i++ ) {
			if ( i > sizePart8int && i < sizeLessSizePart8int ) {
				if ( x1 == null )
					x1 = size - sizePart12Int;
				
				g2d.setColor(new Color( ColorTypeEnum.GreenPigment, ColorAttributeTypeEnum.Background ).getRgbColor() );
				g2d.fillRect(x1, i, borderSizeInt, 1);
				
				x1--;
			}
		}
		for ( int i = size; i > 0; i-- ) {
			if ( i < sizeLessSizePart8int && i > sizePart4Int ) {
				g2d.setColor(new Color( ColorTypeEnum.GreenPigment, ColorAttributeTypeEnum.Background ).getRgbColor() );
				g2d.fillRect(x1, i, borderSizeInt, 1);
				
				x1--;
			}
		}
		
		imageIcon = new ImageIcon( bufferedImage );
		g2d.dispose();
		
		imageIcon.setDescription( ImageTypeEnum.GlobalImageIconOk.toString() );
		return imageIcon;
	}
	
	public ImageIcon createGlobalImageIconError( int size ) {
		ImageIcon imageIcon = null;
		
		BufferedImage bufferedImage = new BufferedImage( size, size, BufferedImage.TYPE_INT_ARGB );
		Graphics2D g2d = bufferedImage.createGraphics();
		
		g2d.setColor(new Color( ColorTypeEnum.Red, ColorAttributeTypeEnum.Background ).getRgbColor() );
		for ( int i = 0; i < size; i++ ) {
			Float sizeFloat = new Float(size) / new Float(10);
			int sizeInt = sizeFloat.intValue();
			if ( sizeInt < 1 )
				sizeInt = 1;
			
			g2d.fillRect(i, i, sizeInt, sizeInt);
		}
		for ( int i = 0; i < size; i++ ) {
			Float sizeFloat = new Float(size) / new Float(10);
			int sizeInt = sizeFloat.intValue();
			if ( sizeInt < 1 )
				sizeInt = 1;
			
			g2d.fillRect(size - sizeInt - i, i, sizeInt, sizeInt);
		}
		
		imageIcon = new ImageIcon( bufferedImage );
		g2d.dispose();
		
		imageIcon.setDescription( ImageTypeEnum.GlobalImageIconError.toString() );
		
		return imageIcon;
	}
	
	public ImageIcon createGlobalImageIconCaution( int size ) {
		ImageIcon imageIcon = null;
		
		BufferedImage bufferedImage = new BufferedImage( size, size, BufferedImage.TYPE_INT_ARGB );
		Graphics2D g2d = bufferedImage.createGraphics();
		
		Float borderSize = new Float(size) / new Float(10);
		int borderSizeInt = borderSize.intValue();
		if ( borderSizeInt < 2 )
			borderSizeInt = 2;
		
		Float halfBorderSize = borderSize / new Float(2);
		int halfBorderSizeInt = halfBorderSize.intValue();
		if ( halfBorderSizeInt < 1 )
			halfBorderSizeInt = 1;
		int sizeLessBorderInt = size - borderSizeInt;
		int sizeMoreBorderInt = size - borderSizeInt;
		
		Float width = new Float(size) / new Float(5);
		int widthInt = width.intValue();
		if ( widthInt < 2 )
			widthInt = 2;
		Float widthHalf = width / new Float(2);
		int widthHalfInt = widthHalf.intValue();
		if ( widthHalfInt < 2 )
			widthHalfInt = 2;
		
		Float sizeHalf = new Float(size) / new Float(2);
		int sizeHalfInt = sizeHalf.intValue();
		
		Float sizePart3 = new Float(size) / new Float(3);
		int sizePart3Int = sizePart3.intValue();
		
		Float sizePart6 = new Float(size) / new Float(6);
		int sizePart6Int = sizePart6.intValue();
		
		Float sizePart8 = new Float(size) / new Float(8);
		
		Float limit = sizePart8 * new Float(5);
		int limitInt = limit.intValue();
		
		Float sizePart12 = new Float(size) / new Float(12);
		int sizePart12Int = sizePart12.intValue();
		
		Integer x1 = null;
		Integer x2 = null;
		
		for ( int i = 0; i < size; i++ ) {
			if ( x1 == null )
				x1 = sizeHalfInt - halfBorderSizeInt;
			if ( x2 == null )
				x2 = sizeHalfInt - halfBorderSizeInt;
			
			g2d.setColor(new Color( ColorTypeEnum.MaroonWeb, ColorAttributeTypeEnum.Background ).getRgbColor() );
			g2d.fillRect(x1, i, borderSizeInt, 1);
			g2d.fillRect(x2, i, borderSizeInt, 1);
			g2d.fillRect(i, sizeLessBorderInt, 1, borderSizeInt);
			
			for (int i2 = 0; i2 < size; i2++) {
				if ( i < sizeMoreBorderInt && i2 >= x1 + borderSizeInt && i2 < x2 ) {
					g2d.setColor(new Color( ColorTypeEnum.YellowEarth, ColorAttributeTypeEnum.Background ).getRgbColor() );
					g2d.fillRect(i2, i, 1, 1);
				}
			}
			
			if ( i > sizePart3Int && i < limitInt ) {
				g2d.setColor(new Color( ColorTypeEnum.BlackBean, ColorAttributeTypeEnum.Background ).getRgbColor() );
				g2d.fillRect(sizeHalfInt - widthHalfInt, i, widthInt, 1);
			} else if ( i > limitInt + sizePart12Int && i < size - sizePart6Int ) {
				g2d.setColor(new Color( ColorTypeEnum.BlackBean, ColorAttributeTypeEnum.Background ).getRgbColor() );
				g2d.fillRect(sizeHalfInt - widthHalfInt, i, widthInt, 1);
			}
			
			if ( Objects.equals(i % 2, 0) == true ) {
				x1--;
				x2++;
			}
		}
		
		imageIcon = new ImageIcon( bufferedImage );
		g2d.dispose();
		
		imageIcon.setDescription( ImageTypeEnum.GlobalImageIconCaution.toString() );
		return imageIcon;
	}
	
	public ImageIcon createGlobalImageIconFolder( int size ) {
		ImageIcon imageIcon = null;
		
		BufferedImage bufferedImage = new BufferedImage( size, size, BufferedImage.TYPE_INT_ARGB );
		Graphics2D g2d = bufferedImage.createGraphics();
		
		Float borderSize = new Float(size) / new Float(10);
		int borderSizeInt = borderSize.intValue();
		if ( borderSizeInt < 2 )
			borderSizeInt = 2;
		
		Float sizeHalf = new Float(size) / new Float(2);
		int sizeHalfInt = sizeHalf.intValue();
		
		Float sizePart8 = new Float(size) / new Float(8);
		int sizePart8Int = sizePart8.intValue();
		
		for ( int i = 0; i < size; i++ ) {
			if ( i < sizePart8Int ) {
				g2d.setColor(new Color( ColorTypeEnum.BlueGray, ColorAttributeTypeEnum.Background ).getRgbColor() );
				for ( int i2 = 0; i2 < sizeHalfInt; i2++ ) {
					g2d.fillRect(i2, i, 1, 1);
				}
			} else {
				g2d.setColor(new Color( ColorTypeEnum.BlueBDazzled, ColorAttributeTypeEnum.Background ).getRgbColor() );
				g2d.fillRect(0, i, borderSizeInt, 1);
				g2d.fillRect(i - sizePart8Int + borderSizeInt, sizePart8Int, 1, borderSizeInt);
				
				g2d.fillRect(size - borderSizeInt, i, borderSizeInt, 1);
				g2d.fillRect(i - sizePart8Int + borderSizeInt, size - borderSizeInt, 1, borderSizeInt);
				
				if ( i >= sizePart8Int + borderSizeInt && i < size - borderSizeInt ) {
					g2d.setColor(new Color( ColorTypeEnum.BlueGray, ColorAttributeTypeEnum.Background ).getRgbColor() );
					for ( int i2 = borderSizeInt; i2 < size - borderSizeInt; i2++ ) {
						g2d.fillRect(i2, i, 1, 1);
					}
				}
			}
		}
		
		imageIcon = new ImageIcon( bufferedImage );
		g2d.dispose();
		
		imageIcon.setDescription( ImageTypeEnum.GlobalImageIconFolder.toString() );
		return imageIcon;
	}
	
	public ImageIcon createGlobalImageIconFile( int size ) {
		ImageIcon imageIcon = null;
		
		BufferedImage bufferedImage = new BufferedImage( size, size, BufferedImage.TYPE_INT_ARGB );
		Graphics2D g2d = bufferedImage.createGraphics();
		
		Float borderSize = new Float(size) / new Float(10);
		int borderSizeInt = borderSize.intValue();
		if ( borderSizeInt < 2 )
			borderSizeInt = 2;
		int borderSizeX2Int = borderSizeInt * 2;
		
		Float sizePart8 = new Float(size) / new Float(8);
		int sizePart8Int = sizePart8.intValue();
		
		Integer cornerBottomRightXy = null;
		Integer cornerBottomRightXy2 = null;
		
		for ( int i = 0; i < size; i++ ) {
			g2d.setColor(new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Background ).getRgbColor() );
			g2d.fillRect(0, i, borderSizeInt, 1);
			g2d.fillRect(i, 0, 1, borderSizeInt);
			
			if ( i < size - borderSizeX2Int ) {
				g2d.fillRect(size - borderSizeInt, i, borderSizeInt, 1);
				g2d.fillRect(i, size - borderSizeInt, 1, borderSizeInt);
			}
			
			if ( i >= borderSizeInt && i < size - borderSizeInt ) {
				g2d.setColor(new Color( ColorTypeEnum.Gainsboro, ColorAttributeTypeEnum.Background ).getRgbColor() );
				for ( int i2 = borderSizeInt; i2 < size - borderSizeInt; i2++ ) {
					g2d.fillRect(i2, i, 1, 1);
				}
			}
			if ( i >= size - borderSizeX2Int - sizePart8Int && i < size - borderSizeInt ) {
				if ( cornerBottomRightXy == null )
					cornerBottomRightXy = i;
				
				g2d.setColor(new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Background ).getRgbColor() );
				g2d.fillRect(cornerBottomRightXy, i, borderSizeInt, 1);
				g2d.fillRect(i, cornerBottomRightXy, 1, borderSizeInt);
			}
			if ( i >= size - borderSizeX2Int ) {
				if ( cornerBottomRightXy2 == null )
					cornerBottomRightXy2 = i + borderSizeInt;
				
				g2d.setColor(new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Background ).getRgbColor() );
				g2d.fillRect(cornerBottomRightXy2, i, borderSizeInt, 1);
				
				cornerBottomRightXy2--;
			}
		}
		
		imageIcon = new ImageIcon( bufferedImage );
		g2d.dispose();
		
		imageIcon.setDescription( ImageTypeEnum.GlobalImageIconFile.toString() );
		return imageIcon;
	}
	
	public ImageIcon createGlobalImageIconSearch( int size ) {
		ImageIcon imageIcon = null;
		
		BufferedImage bufferedImage = new BufferedImage( size, size, BufferedImage.TYPE_INT_ARGB );
		Graphics2D g2d = bufferedImage.createGraphics();
		
		Float borderSize = new Float(size) / new Float(10);
		int borderSizeInt = borderSize.intValue();
		if ( borderSizeInt < 2 )
			borderSizeInt = 2;
		
		Float halfBorderSize = borderSize / new Float(2);
		int halfBorderSizeInt = halfBorderSize.intValue();
		if ( halfBorderSizeInt < 1 )
			halfBorderSizeInt = 1;
		
		Float quartBorderSize = borderSize / new Float(4);
		int quartBorderSizeInt = quartBorderSize.intValue();
		if ( quartBorderSizeInt < 1 )
			quartBorderSizeInt = 1;
		
		int borderSizeX2Int = borderSizeInt * 2;
		
		Float sizeHalf = new Float(size) / new Float(2);
		int sizeHalfInt = sizeHalf.intValue();
		
		Float sizePart4 = new Float(size) / new Float(4);
		int sizePart4Int = sizePart4.intValue();
		
		Float sizePart5 = new Float(size) / new Float(5);
		int sizePart5Int = sizePart5.intValue();
		
		Float sizePart6 = new Float(size) / new Float(6);
		int sizePart6Int = sizePart6.intValue();
		
		Float sizePart12 = new Float(size) / new Float(12);
		int sizePart12Int = sizePart12.intValue();
		if ( sizePart12Int < 1 )
			sizePart12Int = 1;
		
		Float sizePart16 = new Float(size) / new Float(16);
		int sizePart16Int = sizePart16.intValue();
		if ( sizePart16Int < 1 )
			sizePart16Int = 1;
		
		Integer xy1 = null;
		Integer xy2 = null;
		Integer xy3 = null;
		Integer xy4 = null;
		
		for ( int i = 0; i < size; i++ ) {
			if ( i < borderSizeInt || i > size - borderSizeInt ) {
				if ( xy1 == null )
					xy1 = sizeHalfInt - sizePart12Int;
				
				g2d.setColor(new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Background ).getRgbColor() );
				for ( int i2 = 0; i2 < sizePart6Int; i2++ ) {
					g2d.fillRect(xy1 + i2, i, 1, 1);
					g2d.fillRect(i, xy1 + i2, 1, 1);
				}
			} else if ( i < borderSizeX2Int || i > size - borderSizeX2Int ) {
				if ( xy2 == null )
					xy2 = sizeHalfInt - sizePart4Int;
				if ( xy3 == null )
					xy3 = sizeHalfInt + sizePart16Int;
				
				g2d.setColor(new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Background ).getRgbColor() );
				for ( int i2 = 0; i2 < sizePart5Int; i2++ ) {
					g2d.fillRect(xy2 + i2, i, 1, 1);
					g2d.fillRect(i, xy2 + i2, 1, 1);
				}
				for ( int i2 = 0; i2 < sizePart5Int; i2++ ) {
					g2d.fillRect(xy3 + i2, i, 1, 1);
					g2d.fillRect(i, xy3 + i2, 1, 1);
				}
			}
			if ( i > sizePart4Int && i < size - sizePart4Int ) {
				if ( xy4 == null ) {
					xy4 = sizeHalfInt - quartBorderSizeInt;
				}
				
				g2d.setColor(new Color( ColorTypeEnum.Red, ColorAttributeTypeEnum.Background ).getRgbColor() );
				
				for ( int i2 = 0; i2 < halfBorderSizeInt; i2++ ) {
					g2d.fillRect(xy4 + i2, i, 1, 1);
					g2d.fillRect(i, xy4 + i2, 1, 1);
				}
			}
			
		}
		
		imageIcon = new ImageIcon( bufferedImage );
		g2d.dispose();
		
		imageIcon.setDescription( ImageTypeEnum.GlobalImageIconSearch.toString() );
		return imageIcon;
	}
	
	public ImageIcon createGlobalImageIconNotSaved( int size ) {
		ImageIcon imageIcon = null;
		
		BufferedImage bufferedImage = new BufferedImage( size, size, BufferedImage.TYPE_INT_ARGB );
		Graphics2D g2d = bufferedImage.createGraphics();
		
		Float borderSize = new Float(size) / new Float(10);
		int borderSizeInt = borderSize.intValue();
		if ( borderSizeInt < 2 )
			borderSizeInt = 2;
		int borderSizeX2Int = borderSizeInt * 2;
		int borderSizeX3Int = borderSizeInt * 3;
		
		Float width = new Float(size) / new Float(3);
		int widthInt = width.intValue();
		if ( widthInt < 2 )
			widthInt = 2;
		
		Float sizePart8 = new Float(size) / new Float(8);
		int sizePart8Int = sizePart8.intValue();
		Float limit = sizePart8 * new Float(5);
		int limitInt = limit.intValue();
		
		Float sizePart12 = new Float(size) / new Float(12);
		int sizePart12Int = sizePart12.intValue();
		
		Integer cornerTopRightX = null;
		Integer cornerTopRightY = null;
		
		for ( int i = 0; i < size; i++ ) {
			g2d.setColor(new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Background ).getRgbColor() );
			g2d.fillRect(0, i, borderSizeInt, 1);
			
			if ( i < size - borderSizeX2Int )
				g2d.fillRect(i, 0, 1, borderSizeInt);

			if ( i > borderSizeX2Int )
				g2d.fillRect(size - borderSizeInt, i, borderSizeInt, 1);
			
			g2d.fillRect(i, size - borderSizeInt, 1, borderSizeInt);
			
			if ( i >= borderSizeInt && i < size - borderSizeInt ) {
				g2d.setColor(new Color( ColorTypeEnum.GrayMiddle, ColorAttributeTypeEnum.Background ).getRgbColor() );
				for ( int i2 = borderSizeInt; i2 < size - borderSizeInt; i2++ ) {
					g2d.fillRect(i2, i, 1, 1);
				}
			}
			
			if ( i > sizePart8Int && i < limitInt ) {
				g2d.setColor(new Color( ColorTypeEnum.YellowOrange, ColorAttributeTypeEnum.Background ).getRgbColor() );
				g2d.fillRect(widthInt, i, widthInt, 1);
			} else if ( i > limitInt + sizePart12Int && i < size - sizePart8Int ) {
				g2d.setColor(new Color( ColorTypeEnum.YellowOrange, ColorAttributeTypeEnum.Background ).getRgbColor() );
				g2d.fillRect(widthInt, i, widthInt, 1);
			}
			
			if ( i >= size - borderSizeX3Int ) {
				if ( cornerTopRightX == null )
					cornerTopRightX = i;
				if ( cornerTopRightY == null )
					cornerTopRightY = 0;
				
				g2d.setColor(new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Background ).getRgbColor() );
				g2d.fillRect(cornerTopRightX, cornerTopRightY, borderSizeInt, 1);
				
				cornerTopRightX++;
				cornerTopRightY++;
			}
		}
		
		imageIcon = new ImageIcon( bufferedImage );
		g2d.dispose();
		
		imageIcon.setDescription( ImageTypeEnum.GlobalImageIconNotSaved.toString() );
		return imageIcon;
	}
	
}

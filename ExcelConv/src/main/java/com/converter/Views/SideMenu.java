package com.converter.Views;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Autentikacija.KontrolaPristupa;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings({ "serial" })
@Theme("mytheme")
public class SideMenu extends HorizontalLayout {

	public interface MenuClickHandler {
		void click();
	}

	private static final String STYLE_VISIBLE = "valo-menu-visible";
	private final CssLayout contentArea = new CssLayout();
	private final CssLayout menuArea = new CssLayout();
	public final CssLayout menuItemsLayout = new CssLayout();
	private final MenuBar userMenu = new MenuBar();

	private MenuItem userItem;

	private HorizontalLayout logoWrapper;

	private Label menuCaption;
	
	@Autowired
	private KontrolaPristupa kpristupa;

	public SideMenu() {
		super();
		addStyleName(ValoTheme.UI_WITH_MENU);
		Responsive.makeResponsive(this);
		setSizeFull();

		menuArea.setPrimaryStyleName("valo-menu");
		menuArea.addStyleName("sidebar");
		menuArea.addStyleName(ValoTheme.MENU_PART);
		menuArea.addStyleName("no-vertical-drag-hints");
		menuArea.addStyleName("no-horizontal-drag-hints");
		menuArea.setWidth(null);
		menuArea.setHeight("100%");

		menuArea.addComponents(buildTitle());
		menuArea.addComponent(buildUserMenu());

		Button valoMenuToggleButton = new Button("Menu", new ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				if (menuArea.getStyleName().contains(STYLE_VISIBLE)) {
					menuArea.removeStyleName(STYLE_VISIBLE);
				} else {
					menuArea.addStyleName(STYLE_VISIBLE);
				}
			}
		});
		valoMenuToggleButton.setIcon(VaadinIcons.LIST);
		valoMenuToggleButton.addStyleName("valo-menu-toggle");
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
		menuArea.addComponent(valoMenuToggleButton);

		menuItemsLayout.addStyleName("valo-menuitems");
		menuArea.addComponents(
				new Label("&nbsp", com.vaadin.shared.ui.ContentMode.HTML),
				menuItemsLayout);

		contentArea.setPrimaryStyleName("dashboard-panels");
		Responsive.makeResponsive(contentArea);
		contentArea.setSizeFull();

		super.addComponent(menuArea);
		super.addComponent(contentArea);
		setExpandRatio(contentArea, 1);
	}

	
	private Component buildTitle() {
        Label logo = new Label("Excel <strong>Converter</strong>",
                ContentMode.HTML);
        logo.setSizeUndefined();
        HorizontalLayout logoWrapper = new HorizontalLayout(logo);
        logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        logoWrapper.addStyleName("valo-menu-title");
        return logoWrapper;
    }
	private Component buildUserMenu(){
		final MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        userItem = settings.addItem("", new ThemeResource(
                "img/profile-pic-300px.jpg"), null);
        userItem.addItem("Podešavanja profila", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
            	//UI.getCurrent().addWindow(pp.getWindow());
            }
        });
        
        userItem.addSeparator();
        userItem.addItem("Izlogujte se", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
            	kpristupa.sessionDestroy();
                Page.getCurrent().reload();
                //UI.getCurrent().getNavigator().navigateTo(LoginView.VIEW_NAME);
            }
        });
        return settings;
	}
	
	public void addMenuItem(String text, MenuClickHandler handler) {
		addMenuItem(text, null, handler);
	}


	public void addMenuItem(String text, Resource icon, final MenuClickHandler handler) {
		Button button = new Button(text, new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				handler.click();
				menuArea.removeStyleName(STYLE_VISIBLE);
			}
		});
		button.setIcon(icon);
		button.setPrimaryStyleName("valo-menu-item");
		menuItemsLayout.addComponent(button);
	}


	public void addUserMenuItem(String text, MenuClickHandler handler) {
		addUserMenuItem(text, null, handler);
	}


	public void addUserMenuItem(String text, Resource icon, final MenuClickHandler handler) {
		if ("Separator".equalsIgnoreCase(text)) {
			userItem.addSeparator();
			return;
		}
		userItem.addItem(text, icon, new Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				handler.click();
			}
		});
	}


	public void setUserName(String userName) {
		if (userName.length()>30)
			userName = userName.substring(0, 30) + "" + userName.substring(30, userName.length());
		userItem.setText(userName);
	}

	
	public void setUserIcon(Resource icon) {
		userItem.setIcon(icon);
	}

	
	public void setUserMenuVisible(boolean visible) {
		userMenu.setVisible(visible);
	}

	
	public boolean isUserMenuVisible() {
		return userMenu.isVisible();
	}

	
	public void setMenuCaption(String caption) {
		setMenuCaption(caption, null);
	}

	public void setMenuCaption(String caption, Object object) {
		if (menuCaption==null) {
			menuCaption = new Label();
			menuCaption.setWidth("170px");
			menuCaption.setContentMode(ContentMode.HTML);
			logoWrapper.addComponent(menuCaption);
			logoWrapper.setComponentAlignment(menuCaption, Alignment.MIDDLE_CENTER);
		}
		menuCaption.setValue("<strong>" + caption + "</strong>");
	}

	
	public void clearUserMenu() {
		userItem.removeChildren();
	}

	
	public void addNavigation(String text, String navigationState) {
		addNavigation(text, null, navigationState);
	}

	
	public void addNavigation(String text, Resource icon, final String navigationState) {
		addMenuItem(text, icon, new MenuClickHandler() {

			@Override
			public void click() {
				getUI().getNavigator().navigateTo(navigationState);
			}
		});
	}

	
	@Override
	public void removeAllComponents() {
		contentArea.removeAllComponents();
	}

	
	@Override
	public void addComponent(Component c) {
		contentArea.addComponent(c);
	}


	public void setContent(Component content) {
		contentArea.removeAllComponents();
		contentArea.addComponent(content);
	}
}
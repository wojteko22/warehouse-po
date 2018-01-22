import {UserPage} from './user.po';
import {browser, by, element} from "protractor";

describe('webapp App', () => {
  let page: UserPage;

  beforeEach(() => {
    page = new UserPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    element(by.id('mat-input-0')).sendKeys('Adam');
    element(by.id('mat-input-1')).sendKeys('Nowak');
    element(by.id('mat-input-2')).sendKeys('a@dasfasdfasdfsdaf.pl');
    browser.sleep(3000);
    element(by.cssContainingText('.mat-list-text', 'magazynier przyjmujacy')).click();
    expect(element(by.cssContainingText('button', 'Dodaj')).isEnabled)

  });
});

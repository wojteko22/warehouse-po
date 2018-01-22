import {browser, by, element} from 'protractor';

export class UserPage {
  navigateTo() {
    return browser.get('/user');
  }

  getParagraphText() {
    return element(by.css('app-root h1')).getText();
  }
}

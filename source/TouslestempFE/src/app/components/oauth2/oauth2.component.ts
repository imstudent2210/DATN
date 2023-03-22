// import { Component } from '@angular/core';
// import { lastValueFrom } from 'rxjs';
// import { Oauth2Service, UserInfo } from 'src/app/services/oauth2.service';

// @Component({
//   selector: 'app-oauth2',
//   templateUrl: './oauth2.component.html',
//   styleUrls: ['./oauth2.component.css']
// })
// export class Oauth2Component {
//   title = 'OAuth2 - Google API';

//   mailSnippets: string[] = []
//   userInfo?: UserInfo

//   constructor(private readonly googleApi: Oauth2Service) {
//     googleApi.userProfileSubject.subscribe( info => {
//       this.userInfo = info
//     })
//   }

//   isLoggedIn(): boolean {
//     return this.googleApi.isLoggedIn()
//   }

//   logout() {
//     this.googleApi.signOut()
//   }

//   async getEmails() {
//     if (!this.userInfo) {
//       return;
//     }

//     const userId = this.userInfo?.info.sub as string
//     const messages = await lastValueFrom(this.googleApi.emails(userId))
//     messages.messages.forEach( (element: any) => {
//       const mail = lastValueFrom(this.googleApi.getMail(userId, element.id))
//       mail.then( mail => {
//         this.mailSnippets.push(mail.snippet)
//       })
//     });
//   }
// }

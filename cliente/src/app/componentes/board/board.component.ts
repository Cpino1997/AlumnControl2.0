import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';
import { StorageService } from 'src/app/_services/storage.service';
import { EventBusService } from 'src/app/_shared/event-bus.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})

export class BoardComponent implements OnInit{
  @Input() isLoggedIn: boolean = false;
  private roles: string[] = [];
  showUserBoard = false;
  showAdminBoard = false;
  showProfeBoard = false;
  showHome = true;
  username?: string;

  constructor(private breakpointObserver: BreakpointObserver,
    private storageService: StorageService,
    private authService: AuthService,
    private eventBusService: EventBusService) {}

  ngOnInit() {
    if (this.isLoggedIn) {
    const user = this.storageService.getUser();
    this.roles = user.roles;
    this.showHome = false; //sacar
    this.showUserBoard = this.roles.includes('ROLE_USER');
    this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
    this.showProfeBoard = this.roles.includes('ROLE_MODERATOR');
    this.username = user.username;
  }
}

}

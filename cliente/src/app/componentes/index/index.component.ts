import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';
import { StorageService } from 'src/app/_services/storage.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  constructor(private authService : AuthService, private storageService : StorageService) { }
  isLoggedIn = false;
  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
  }
  logout(): void {
    this.authService.logout().subscribe({
      next: res => {
        console.log(res);
        this.storageService.clean();
        alert('sesion cerrada con exito!')
        window.location.reload();
      },
      error: err => {
        console.log(err);
      }
    });
  }
}

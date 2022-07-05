import React from 'react';
import './Header.css';
import logo from './logo.svg';
import logo1 from './Group 20399.svg';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button'

const Header = () => (
  <>
  <div className="header">
      <div className='logo1'>
        <img src={logo1} alt="ABC" />
    </div>
    <div className='logo'>
        <img src={logo} alt="HighRadius" />
    </div>
</div>

  <div className='invoice'>
    <p>Invoice List</p>
  </div>




  </>
  
)
export default Header;
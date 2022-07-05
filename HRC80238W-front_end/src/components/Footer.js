import { makeStyles } from '@mui/material'
import React from 'react'
import '../App.css';
export const Footer = () => {
  let footerstyle = {
    position: "relative",
    

    top: "3vh",
      };
  return (
    <footer style={footerstyle} >
      <p color='white'><a href="#">Privacy Policy</a>| &copy; 2022 Highradius Corporation. All right reserved.  </p>
    </footer>
  )
}
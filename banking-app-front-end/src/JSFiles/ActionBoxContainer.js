import React from 'react';
import '../CSSFiles/ActionBoxContainer.css'; 

const ActionBoxContainer = ({ children }) => {
  return <div className="action-box-container">{children}</div>;
};

export default ActionBoxContainer;